package com.fox.platform.channel.infra.serv;

import com.google.common.net.MediaType;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.Router;

/**
 * @author hector.hidalgo
 * Configure parameters on Router object.
 */
public interface ConfigurationEndpoint {
	
	String NO_STORE="no-store, no-cache";
	String CONTENT_TYPE="content-type";
	String APPLICATION_JSON="application/json; charset=UTF-8";
	String ROOT_ROUTER="/*";
	

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
	static Router configureRouter(Router router,Vertx vertx) {
		router = Router.router(vertx);
		router.route().handler(ctx -> {
			ctx.response().putHeader(HttpHeaders.CACHE_CONTROL,NO_STORE);
			ctx.response().putHeader(CONTENT_TYPE, APPLICATION_JSON);
			ctx.next();
		});
		router.route(ROOT_ROUTER)
        .produces(MediaType.JSON_UTF_8.toString())
        .consumes(MediaType.JSON_UTF_8.toString());
		
		return router;
	}
}
