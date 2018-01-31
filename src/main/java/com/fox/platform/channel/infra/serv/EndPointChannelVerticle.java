package com.fox.platform.channel.infra.serv;

import com.fox.platform.channel.cfg.impl.VertxChannelModule;
import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;
import com.google.inject.Guice;
import com.google.inject.Inject;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
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
		Guice.createInjector(new VertxChannelModule(vertx)).injectMembers(this);
		startFuture.setHandler(handler -> {
			if (handler.succeeded()) {
				LOGGER.debug("***STARTING HTTP SERVICE....**** ");
				router= configureRouter(router,vertx);
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

	@Override
	public Router configureRouter(Router router, Vertx vertx) {
		router = Router.router(vertx);
		router.route().handler(ctx -> {
			ctx.response().putHeader(HttpHeaders.CACHE_CONTROL,NO_STORE);
			ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, MediaType.JSON_UTF_8.toString());
			ctx.next();
		});
		router.route(ROOT_ROUTER)
        .produces(MediaType.JSON_UTF_8.toString())
        .consumes(MediaType.JSON_UTF_8.toString());
		
		return router;
	}
	
}
