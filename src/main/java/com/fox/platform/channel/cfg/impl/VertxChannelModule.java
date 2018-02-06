package com.fox.platform.channel.cfg.impl;

import com.fox.platform.channel.infra.serv.ChannelOperationsImpl;
import com.fox.platform.channel.infra.serv.IChannelOperations;
import com.fox.platform.lib.fac.WebClientFactory;
import com.fox.platform.lib.fac.WebClientFactoryImpl;
import com.google.inject.AbstractModule;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

/**
 * Create configuration for Dependency Injection
 * @author hector.hidalgo
 *
 */
public class VertxChannelModule extends AbstractModule {

	private Vertx vertx;

	/**
	 * Constructor for vertx
	 * @param vertx
	 */
	public VertxChannelModule(Vertx vertx) {
		this.vertx = vertx;
	}
	
	/**
	 * configure all dependency injection
	 */
	protected void configure() {
		bind(EventBus.class).toInstance(vertx.eventBus());
		bind(Vertx.class).toInstance(vertx);
		bind(IChannelOperations.class).to(ChannelOperationsImpl.class);
	}
}
