package com.fox.platform.channel.cfg;

import com.fox.platform.lib.cfg.ServiceConfig;
import io.vertx.core.http.HttpMethod;

/**
 * Main configuration from Application Channel
 * @author hector.hidalgo
 *
 */
public interface IConfigurationCore extends ServiceConfig {
	
	String NODE_MAIN_CONFIGURATION="infoMicroServiceConfiguration";
	String EVENT_PROXY_CHANNEL="omnix_proxy";
	
	/**
	 * get Root Path
	 * @return rootPath
	 */
	 String getRootPath();
	 /**
	  * get Post Method
	  * @return postMethod
	  */
	 HttpMethod getPostMethod();
	 
	 /**
	  * get Event Buss Channel Proxy
	  * @return proxy
	  */
	 String getEvenBusProxy();
	 
	 /**
	  * Api Rest Path
	  * @return apiRestPath
	  */
	 String getApiRestPath();
	 
	 /**
	  * Post from Omnix server
	  * @return portOmnix
	  */
	 int getPortOmnix();
	 
	 /**
	  * Uri from Omnix server
	  * @return uriOmnix
	  */
	 String getUriOmnix();
	 
	 /**
	  * query Omnix service
	  * @return queryOmnix
	  */
	 String getQueryOmnix();
	 
	 /**
	  * Host name Omnix
	  * @return hostOmnix
	  */
	 String getHostOmnix();
}
