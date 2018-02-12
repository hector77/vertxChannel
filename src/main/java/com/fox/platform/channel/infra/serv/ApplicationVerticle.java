package com.fox.platform.channel.infra.serv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fox.platform.channel.cfg.IConfigurationCore;
import com.fox.platform.channel.cfg.impl.ConfigurationCoreImpl;
import com.fox.platform.lib.cfg.ConfigLibFactory;
import com.fox.platform.lib.vrt.AbstractConfigurationVerticle;

import io.vertx.core.Future;

/**
 * Create Initial configuration for application
 * @author hector.hidalgo
 *
 */
public class ApplicationVerticle extends AbstractConfigurationVerticle {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationVerticle.class);

	protected IConfigurationCore iConfigurationCore;
	
	@Override
	public void start(Future<Void> startFuture) throws Exception {
		LOGGER.debug("***STARTING APPLICATION CHANNEL MICROSERVICE****");

		iConfigurationCore = (IConfigurationCore) ConfigLibFactory.FACTORY.createServiceConfig(
				config(),
				IConfigurationCore.NODE_MAIN_CONFIGURATION, 
				ConfigurationCoreImpl.class);
		super.start(startFuture);
	}
	 public void onConfigChange() {
		    if (LOGGER.isInfoEnabled()) {
		    	LOGGER.info("Change Content Service Config");
		    }
		  }
}
