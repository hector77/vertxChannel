package com.fox.platform.channel.infra.serv;

import io.vertx.ext.web.RoutingContext;

/**
 * Contain all HTTP methods for REST API Channel
 * @author hector.hidalgo
 *
 */
public interface IChannelOperations {
	
	/**
	 * Execute business Post Channel By Id Country 
	 * @param routingContext context route
	 */
	void executePostChannelByCountry(RoutingContext routingContext);

}
