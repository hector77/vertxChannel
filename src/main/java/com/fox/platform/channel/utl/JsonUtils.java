package com.fox.platform.channel.utl;


import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;


/**
 * Contain utilities JsonString operations
 * @author hector.hidalgo
 *
 */
public interface JsonUtils {
	Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
	String COUNTRY_ID="{countryParam}";
	
	public static JsonObject parserQuery(String query, String countryId) {
	    try {
	      query = query.replace(COUNTRY_ID, countryId);
	      return new JsonObject(query);

	    } catch (Exception ex) {
	      LOGGER.error("Error parserQuery: ", ex);
	      return null;
	    }
	  }

}
