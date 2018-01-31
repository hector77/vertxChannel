package com.fox.platform.contentserv.infra.serv;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.fox.platform.channel.cfg.IConfigurationCore;
import com.fox.platform.channel.cfg.impl.ConfigurationCoreImpl;
import com.fox.platform.channel.infra.serv.EndPointChannelVerticle;
import com.fox.platform.channel.infra.serv.ProxyChannelVerticle;
import com.fox.platform.channel.utl.JsonUtils;
import com.fox.platform.channel.utl.JsonUtilsTest;
import com.fox.platform.lib.cfg.ConfigLibFactory;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.impl.MimeMapping;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

/**
 * Test End-Point for ChannelVerticle
 * 
 * @author hector.hidalgo
 *
 */
@RunWith(VertxUnitRunner.class)
public class EndPointChannelVerticleTest {

	private IConfigurationCore iConfigurationCore;
	private static final Logger LOGGER = LoggerFactory.getLogger(EndPointChannelVerticleTest.class);
	private static final String JSON_MIMETYPE = MimeMapping.getMimeTypeForExtension("json");
	private static final String URL_DEFAULT_CONFIG = "/default-config.json";
	private static final String NO_CACHE = "no-cache";
	private static final String LENGTH = "0";
	private static final String REQUEST_URL = "localhost";
	private static final int PORT=9090;

	Vertx vertx;
	

	@Before
	public void before(TestContext context) throws IOException {
		JsonObject configFile = JsonUtilsTest.getJsonObjectByPath(URL_DEFAULT_CONFIG);
		JsonObject configFail = JsonUtilsTest.getJsonObjectByPath("");
		if(configFail==null) {
			DeploymentOptions options = new DeploymentOptions().setConfig(configFile);

			vertx = Vertx.vertx();
			vertx.deployVerticle(EndPointChannelVerticle.class.getName(), options, context.asyncAssertSuccess());
			vertx.deployVerticle(ProxyChannelVerticle.class.getName(), options, context.asyncAssertSuccess());
		}
		
	}

	@After
	public void after(TestContext context) {
		vertx.close(context.asyncAssertSuccess());
	}

	@Test
	public void callEndPointChannelVerticleSuccess(TestContext ctx) {

		Async async = ctx.async();
		WebClient client = WebClient.create(vertx);
		client.post(PORT, REQUEST_URL, "/channel?countryId=MX").ssl(false)
				.putHeader(HttpHeaders.CONTENT_LENGTH.toString(), LENGTH)
				.putHeader(HttpHeaders.CONTENT_TYPE.toString(), JSON_MIMETYPE)
				.putHeader(HttpHeaders.CACHE_CONTROL.toString(), NO_CACHE).send(response -> {
					if (response.succeeded()) {
						LOGGER.info("*****EXECUTION END POINT CHANELL***");
						HttpResponse<Buffer> data = response.result();
						LOGGER.info("STATUS CODE : " + data.bodyAsString());
						LOGGER.info("DATA : " + data.statusCode());
						ctx.assertNotNull(data.bodyAsJsonObject());
						ctx.assertEquals(200, data.statusCode());
					}
					client.close();
					async.complete();
				});
	}
	
	@Test
	public void callEndPointChannelVerticleFail(TestContext ctx) {
		
		Async async = ctx.async();
		WebClient client = WebClient.create(vertx);
		client.post(PORT, REQUEST_URL, "/channel?country=MX").ssl(false)
				.putHeader(HttpHeaders.CONTENT_LENGTH.toString(), LENGTH)
				.putHeader(HttpHeaders.CONTENT_TYPE.toString(), JSON_MIMETYPE)
				.putHeader(HttpHeaders.CACHE_CONTROL.toString(), NO_CACHE).send(response -> {
					if (response.succeeded()) {
						LOGGER.info("*** ERROR DATA ***");
						HttpResponse<Buffer> data = response.result();
						LOGGER.info("DATA : " + data.bodyAsString());
						LOGGER.info("STATUS CODE : " + data.statusCode());
					    ctx.assertEquals(500, data.statusCode());
						client.close();
						async.complete();
					}
					
				});
		
	}
	@Test
	  public void parserQuerySuccess() throws Exception {
	    JsonObject configFile = JsonUtilsTest.getJsonObjectByPath(URL_DEFAULT_CONFIG);
	    iConfigurationCore =
	        (IConfigurationCore) ConfigLibFactory.FACTORY.createServiceConfig(configFile,
	        		IConfigurationCore.NODE_MAIN_CONFIGURATION, ConfigurationCoreImpl.class);
	    JsonObject jsonObject =
	        JsonUtils.parserQuery(iConfigurationCore.getQueryOmnix(), "MX");
	    assertNotNull(jsonObject);

	  }
	@Test
	  public void parserQueryFail() throws Exception {
		 JsonObject configFile = JsonUtilsTest.getJsonObjectByPath(URL_DEFAULT_CONFIG);
		    iConfigurationCore =
		        (IConfigurationCore) ConfigLibFactory.FACTORY.createServiceConfig(configFile,
		        		IConfigurationCore.NODE_MAIN_CONFIGURATION, ConfigurationCoreImpl.class);
	    
	    JsonObject jsonObject =
	        JsonUtils.parserQuery(iConfigurationCore.getQueryOmnix(),null);
	    assertNull(jsonObject);

	  }
	
	
	
}
	

	