package com.fox.platform.channel.infra.serv;

import java.util.Optional;

import com.fox.platform.channel.cfg.IConfigurationCore;
import com.fox.platform.channel.exc.ChannelException;
import com.google.inject.Inject;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;


/**
 * Contain all HTTP implementation methods for REST API Channel
 * @author hector.hidalgo
 *
 */
public class ChannelOperationsImpl  implements IChannelOperations {
	private static final Logger LOGGER = LoggerFactory.getLogger(ChannelOperationsImpl.class);
	
	private Vertx vertx;
	private String countryId;
	
	@Inject
	public ChannelOperationsImpl(Vertx vertx) {
		this.vertx = vertx;
	}
	
	@Override
	public void executePostChannelByCountry(RoutingContext routingContext) {
		try {
			LOGGER.debug("*** executePostChannelByCountry endpoint service ***");
			countryId = Optional.ofNullable(routingContext.request().getParam("countryId"))
					.orElseThrow(() -> new ChannelException("Invalid country Id"));
			LOGGER.debug("COUNTRY_ID: " + countryId);
			 JsonObject data = new JsonObject();
		      data.put("country", countryId);

			vertx.eventBus().send(IConfigurationCore.EVENT_PROXY_CHANNEL, data, response -> {

				if (response.succeeded()) {
					routingContext.response().setStatusCode(HttpResponseStatus.OK.code())
							.end(new JsonObject(response.result().body().toString()).encodePrettily());
				} else {
					LOGGER.error("ERROR PROCESSING CONEXION TO WS CHANEL ON: ", response.cause());
					routingContext
					.response()
					.setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
					.putHeader(HttpHeaders.CONTENT_TYPE, TEXT_MIMETYPE)
					.end(response.cause().getStackTrace().toString());
				}
			});
		} catch (Exception e) {
			routingContext
			.response()
			.setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
			.putHeader(HttpHeaders.CONTENT_TYPE, TEXT_MIMETYPE)
			.end(e.getMessage());
			LOGGER.error("ERROR ON POST ChannelByCountry: ", e);
		}
	}	
}
