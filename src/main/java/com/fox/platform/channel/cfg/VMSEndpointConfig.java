package com.fox.platform.channel.cfg;

import com.fox.platform.lib.cfg.EndpointConfig;

/**
 * Interface that extends the behaviour of an Endpoint
 * add the method to obtain the URL by Country
 * @author h.gonzalez
 *
 */
public interface VMSEndpointConfig extends EndpointConfig {	
	public String getUrlPathByCountry(String country);
}
