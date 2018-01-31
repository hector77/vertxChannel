package com.fox.platform.contentserv.cfg;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.fox.platform.channel.cfg.IConfigurationCore;
import com.fox.platform.channel.cfg.impl.ConfigurationCoreImpl;
import com.fox.platform.channel.utl.JsonUtilsTest;
import com.fox.platform.lib.cfg.ConfigLibFactory;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.junit.VertxUnitRunner;

/**
 * Configuration Proxy Service Test
 * @author hector.hidalgo
 *
 */
@RunWith(VertxUnitRunner.class)
public class ConfigurationServiceTest {

	private IConfigurationCore iConfigurationCore;
	private static final String URL_DEFAULT_CONFIG = "/default-config.json";

	@Test
	public void loadConfig() {

		JsonObject configFile = JsonUtilsTest.getJsonObjectByPath(URL_DEFAULT_CONFIG);
		iConfigurationCore = (IConfigurationCore) ConfigLibFactory.FACTORY.createServiceConfig(configFile,
				IConfigurationCore.NODE_MAIN_CONFIGURATION, ConfigurationCoreImpl.class);

		iConfigurationCore.getEvenBusProxy();
		assertNotNull(iConfigurationCore);
	}

}
