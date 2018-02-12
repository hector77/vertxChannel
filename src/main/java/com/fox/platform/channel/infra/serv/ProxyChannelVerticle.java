package com.fox.platform.channel.infra.serv;

import com.fox.platform.channel.cfg.ContentProxyConfig;
import com.fox.platform.channel.cfg.IConfigurationCore;
import com.fox.platform.channel.cfg.impl.VMSModule;
import com.fox.platform.channel.dom.ent.Root;
import com.fox.platform.channel.vo.MPXResponse;
import com.fox.platform.lib.cbr.SwitchCircuitBreaker;
import com.fox.platform.lib.cbr.exc.KillswitchException;
import com.fox.platform.lib.cbr.fac.CircuitBreakerFactory;
import com.google.common.net.MediaType;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;

import io.vertx.circuitbreaker.CircuitBreakerState;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

/**
 * Contain business from WebClient to execute Omnix micro services
 * @author hector.hidalgo
 *
 */
public class ProxyChannelVerticle extends ApplicationVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProxyChannelVerticle.class);
  private static final String LENGTH = "0";
  
  protected ContentProxyConfig proxyVMSConfig;
  private SwitchCircuitBreaker circuitBreaker;
  private WebClient client;
  private JsonObject jsonObject;
  @Inject
  private CircuitBreakerFactory circuitBreakerFactory;

  public void start(Future<Void> future) throws Exception {
	    try {
	      Future<Void> startFuture = Future.future();
	      super.start(startFuture);
	      startFuture.setHandler(handler -> {
	        if (handler.succeeded()) {
	          proxyVMSConfig = iConfigurationCore.getProxyVMS();
	          LOGGER.info(
	              "Starting ProxyVerticle with config: " + JsonObject.mapFrom(proxyVMSConfig).encode());

	          Guice.createInjector(new VMSModule(vertx, proxyVMSConfig)).injectMembers(this);
	          circuitBreaker = circuitBreakerFactory.createCircuitBreaker();

					vertx.eventBus().<JsonObject>consumer(IConfigurationCore.EVENT_PROXY_CHANNEL,
	              request -> circuitBreaker
	                  .<MPXResponse>executeWithKillswitch(
	                      circuitFuture -> executeCall(circuitFuture, request),
	                      this::replyOnCircuitOpen, this::replyOnKillswitch)
	                  .setHandler(result -> proccessResponse(result, request)));

	          future.complete();

	        } else {
	        	LOGGER.error(deploymentID() + " Error launching ProxyChannelVerticle: "
	              + handler.cause().getMessage(), handler.cause());
	          future.fail(handler.cause());
	        }


	      });

	    } catch (Exception e) {
	      future.fail(e);
	      LOGGER.error("ERROR PROXY", e);
	    }


	  }

  @Override
  public void onConfigChange() {
    super.onConfigChange();
    if (circuitBreaker.getOptions().isKillswitch() != proxyVMSConfig.getCircuitBreaker()
        .isKillswitch()) {
      circuitBreaker.getOptions().setKillswitch(proxyVMSConfig.getCircuitBreaker().isKillswitch());
    }
  }

  /**
   * Perform the request to MPX endpoint uses Vert.x Web Client
   * 
   * @param future object with the result of the call
   * @param request Message receives through event bus
   */
  @Trace(dispatcher = true, async = true)
  private void executeCall(Future<MPXResponse> future, Message<JsonObject> request) {
	  client = createWebClient(vertx);
	  client.post(iConfigurationCore.getPortOmnix(), iConfigurationCore.getHostOmnix(), iConfigurationCore.getUriOmnix())
      .ssl(true).putHeader(HttpHeaders.CONTENT_LENGTH.toString(), LENGTH)
      .putHeader(HttpHeaders.CONTENT_TYPE.toString(), MediaType.JSON_UTF_8.toString())
      .sendJson(jsonObject, response -> {
        if (response.succeeded()) {
          HttpResponse<Buffer> data = response.result();
          Root root = data.bodyAsJsonObject().mapTo(Root.class);
          request.reply(JsonObject.mapFrom(root));
        } else {
        	LOGGER.error("ERROR MESSAGE OPERATION ON: ",
              response.cause().getStackTrace().toString());
        	request.fail(response.hashCode(), response.cause().getMessage());
        }
      });
	  client.close();
  }

  /**
   * Create a WebClient 
   * @param vertx value
   * @return WebClient client
   */
  public WebClient createWebClient(Vertx vertx){
	return  WebClient.create(vertx);
	  
  }
  /**
   * Make a MPXResponse when the Circuit Breaker is opened, this reply send an error
   * 
   * @param openCircuitError Fail that causes the Circuit Breaker go to open state
   * @return MPXResponse object, with an error and says that the circuit is open
   */
  private MPXResponse replyOnCircuitOpen(Throwable openCircuitError) {

    NewRelic.incrementCounter(
        "ContentService/ProxyMPX/Counter/Fallback/" + circuitBreaker.state().toString());
    LOGGER.error("Circuit on open state: " + openCircuitError.getClass().getName(),
        openCircuitError);

    MPXResponse temporalStatus =
        new MPXResponse(circuitBreaker.state(), true, openCircuitError.getMessage());

    if (LOGGER.isDebugEnabled()) {
    	LOGGER.debug("Create Open Circuit reply : " + temporalStatus.toJson().toString());
    }

    return temporalStatus;
  }

  private MPXResponse replyOnKillswitch() {
    return replyOnCircuitOpen(new KillswitchException());
  }

  /**
   * Build and send a reply to Event Bus with the search response of MPX
   * 
   * @param result response from MPX or Open Circuit Breaker
   * @param request Original message to do the reply
   */
  private void proccessResponse(AsyncResult<MPXResponse> result, Message<JsonObject> request) {
    if (result.succeeded()) {
      MPXResponse mpxResponse = result.result();
      request.reply(mpxResponse.toJson());
    } else {
      MPXResponse mpxError =
          new MPXResponse(CircuitBreakerState.CLOSED, true, result.cause().getMessage());
      request.reply(mpxError.toJson());
    }


  }
/*
  public void onMessage(Message<String> message) {

	    try {
	    	LOGGER.debug("*** EXECUTE onMessage WS ON OMNIX SERVICE  ***");
	      jsonObject =JsonUtils.parserQuery(iConfigurationCore.getQueryOmnix(), message.body());
	      executeClientWebOmnix(message);
	    } catch (Exception ex) {
	    	LOGGER.error("***ERROR MESSAGE OPERATION ON: ", ex);
	      message.fail(ex.hashCode(), ex.getCause().getMessage());
	    }
	  }

  */
  
}
