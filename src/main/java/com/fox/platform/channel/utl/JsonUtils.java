package com.fox.platform.channel.utl;

import com.fox.platform.channel.exc.ChannelException;


import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;


/**
 * Contain utilities JsonString operations
 * @author hector.hidalgo
 *
 */
public class JsonUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
	private static final String COUNTRY_ID="{countryParam}";
	
	public static JsonObject parserQuery(String query, String countryId) {
	    try {
	      query = query.replace(COUNTRY_ID, countryId);
	      return new JsonObject(query);

	    } catch (ChannelException ex) {
	      LOGGER.error("Error parserQuery: ", ex);
	      return null;
	    }
	  }

}
