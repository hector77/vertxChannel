package com.fox.platform.channel.infra.serv;

import io.vertx.core.http.impl.MimeMapping;
import io.vertx.ext.web.RoutingContext;

/**
 * Contain all HTTP methods for REST API Channel
 * @author hector.hidalgo
 *
 */
public interface IChannelOperations {
	
   String TEXT_MIMETYPE = MimeMapping.getMimeTypeForExtension("text");
	
	/**
	 * Execute business Post Channel By Id Country 
	 * @param routingContext context route
	 */
	void executePostChannelByCountry(RoutingContext routingContext);

}
