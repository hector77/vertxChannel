package com.fox.platform.channel.exc;

/**
 * Handle Runtime Exception for Channel Business
 * @author hector.hidalgo
 *
 */
public class ChannelException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Only String massage without root cause
   * @param message 
   */
  public ChannelException(String message) {
    super(message);
  }


}
