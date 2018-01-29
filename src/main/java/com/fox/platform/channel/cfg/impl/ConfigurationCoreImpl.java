package com.fox.platform.channel.cfg.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fox.platform.channel.cfg.IConfigurationCore;
import com.fox.platform.lib.cfg.impl.MergeHttpServerOptions;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;

/**
 * Implementation to Configuration for Channel Rest API 
 * @author hector.hidalgo
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigurationCoreImpl implements IConfigurationCore {
	
	private String apiRestPath;
	private int portOmnix;
	private String hostOmnix;
	private String uriOmnix;
	private MergeHttpServerOptions httpServerOptions;
	private String queryOmnix;
	private String evenBusProxy;
	private String rootPath;
	private HttpMethod postMethod;

	@Override
	public String getApiRestPath() {
		return apiRestPath;
	}
	@Override
	public int getPortOmnix() {
		return portOmnix;
	}
	@Override
	public String getHostOmnix() {
		return hostOmnix;
	}
	@Override
	public String getUriOmnix() {
		return uriOmnix;
	}
	@Override
	public String getQueryOmnix() {
		return queryOmnix;
	}
	@Override
	public String getEvenBusProxy() {
		return evenBusProxy;
	}
	@Override
	public String getRootPath() {
		return rootPath;
	}
	@Override
	public HttpMethod getPostMethod() {
		return postMethod;
	}
	@Override
	public HttpServerOptions getHttpServerOptions() {
		return httpServerOptions;
	}
}
