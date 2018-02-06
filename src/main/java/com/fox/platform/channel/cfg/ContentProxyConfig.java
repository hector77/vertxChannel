package com.fox.platform.channel.cfg;

import com.fox.platform.lib.cbr.cfg.CircuitBreakerProxyConfig;
import com.fox.platform.lib.cfg.ProxyConfig;

/**
 * Interface to join the behaviour of the Proxy Config 
 * and Proxy Config with Circuit Breaker, to simplify the
 * implementation and uses
 * @author h.gonzalez
 *
 */
public interface ContentProxyConfig extends ProxyConfig, CircuitBreakerProxyConfig{

}
