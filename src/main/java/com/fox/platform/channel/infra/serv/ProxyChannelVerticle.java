package com.fox.platform.channel.infra.serv;

import com.fox.platform.channel.cfg.IConfigurationCore;
import com.fox.platform.channel.dom.ent.Root;
import com.fox.platform.channel.exc.ChannelException;
import com.fox.platform.channel.utl.JsonUtils;
import com.google.common.net.MediaType;

import io.vertx.core.Future;
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
  
  private WebClient client;
  private JsonObject jsonObject;

  @Override
  public void start(Future<Void> future) throws Exception {

    try {
      Future<Void> startFuture = Future.future();
      super.start(startFuture);
      vertx.eventBus().consumer(IConfigurationCore.EVENT_PROXY_CHANNEL, this::onMessage);
      future.complete();
    } catch (ChannelException e) {
      future.complete();
      LOGGER.error("***ERROR MESSAGE OPERATION ON: ", e);
    }
  }
  
  /**
   * Get message from WebClient 
   * @param message parameters from Request rest service
   */
  public void onMessage(Message<String> message) {

    try {
      LOGGER.debug("*** EXECUTE onMessage WS ON OMNIX SERVICE  ***");
      client = WebClient.create(vertx);
      jsonObject =JsonUtils.parserQuery(iConfigurationCore.getQueryOmnix(), message.body());
      executeClientWebOmnix(message);
    } catch (ChannelException e) {
      message.fail(e.hashCode(), e.getCause().getMessage());
      LOGGER.error("***ERROR MESSAGE OPERATION ON: ", e);
    } catch (Exception ex) {
    	LOGGER.error("***ERROR MESSAGE OPERATION ON: ", ex);
      message.fail(ex.hashCode(), ex.getCause().getMessage());
    }
  }
  
  /**
   * Execute WebClient to Omnix
   * @param message parameters from Request rest service
   */
  public void executeClientWebOmnix(Message<String> message) {
	  client.post(iConfigurationCore.getPortOmnix(), iConfigurationCore.getHostOmnix(), iConfigurationCore.getUriOmnix())
      .ssl(true).putHeader(HttpHeaders.CONTENT_LENGTH.toString(), LENGTH)
      .putHeader(HttpHeaders.CONTENT_TYPE.toString(), MediaType.JSON_UTF_8.toString())
      .sendJson(jsonObject, response -> {
        if (response.succeeded()) {
          HttpResponse<Buffer> data = response.result();
          Root root = data.bodyAsJsonObject().mapTo(Root.class);
          message.reply(JsonObject.mapFrom(root));
        } else {
        	LOGGER.error("ERROR MESSAGE OPERATION ON: ",
              response.cause().getStackTrace().toString());
          message.fail(response.hashCode(), response.cause().getMessage());
        }
      });
	  client.close();
  }
}
