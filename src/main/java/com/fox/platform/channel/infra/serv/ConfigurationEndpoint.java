package com.fox.platform.channel.infra.serv;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

/**
 * @author hector.hidalgo Configure parameters on Router object.
 */
public interface ConfigurationEndpoint {

	String NO_STORE =  "no-cache";
	String ROOT_ROUTER = "/*";

	/**
	 * mapping all methods for REST API
	 */
	void mappingRestApi();

	/**
	 * Run HttpServer
	 * @param future
	 */
	void runHttpServer(Future<Void> future);

	/**
	 * Configure parameters for Router
	 * @param router value
	 * @param vertx value
	 * @return Router configuration
	 */
	Router configureRouter(Router router, Vertx vertx);

	
}
