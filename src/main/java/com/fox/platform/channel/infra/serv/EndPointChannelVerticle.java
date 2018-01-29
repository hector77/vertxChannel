package com.fox.platform.channel.infra.serv;

import com.fox.platform.channel.cfg.impl.ConfigurationService;
import com.google.inject.Guice;
import com.google.inject.Inject;

import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;

/**
 * Entrance for Channel EndPoint
 * @author hector.hidalgo
 *
 */
public class EndPointChannelVerticle extends ApplicationVerticle implements ConfigurationEndpoint {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EndPointChannelVerticle.class);
	
	private Router router;
	@Inject
	private IChannelOperations iChannelOperations;
	
	@Override
	public void start(Future<Void> future) throws Exception {
	
		Future<Void> startFuture = Future.future();
		super.start(startFuture);
		Guice.createInjector(new ConfigurationService(vertx)).injectMembers(this);
		startFuture.setHandler(handler -> {
			if (handler.succeeded()) {
				LOGGER.debug("***STARTING HTTP SERVICE....**** ");
				router= ConfigurationEndpoint.configureRouter(router,vertx);
				mappingRestApi();
				runHttpServer(future);
			} else {
				future.fail(handler.cause());
			}
		});
	}
	
	@Override
	public void mappingRestApi() {
		router.route(iConfigurationCore.getPostMethod(), iConfigurationCore.getApiRestPath())
					 .handler(iChannelOperations::executePostChannelByCountry);
	}
	
	@Override
	public void runHttpServer(Future<Void> future) {
		vertx.createHttpServer(iConfigurationCore.getHttpServerOptions()).requestHandler(router::accept)
				.listen(response -> {
					if (response.succeeded()) {
						future.complete();
					} else {
						future.fail(response.cause());
					}
				});
	}
	
}
