package com.fox.platform.channel.utl;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;


import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/***
 * Json Util for TesCases
 * @author hector.hidalgo
 *
 */
public interface JsonUtilsTest {
	
	Logger LOGGER = LoggerFactory.getLogger(JsonUtilsTest.class);
	
	 static JsonObject getJsonObjectByPath(String resourcePath) {
	    try {
	      InputStream in = JsonUtilsTest.class.getResourceAsStream(resourcePath);
	      String jsonFile = IOUtils.toString(in, "UTF-8");
	      return new JsonObject(jsonFile);
	    } catch (Exception ex) {
	    	LOGGER.error(ex.getStackTrace().toString());
	      return null;
	    }
	  }

}
