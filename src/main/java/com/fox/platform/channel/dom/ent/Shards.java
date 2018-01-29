package com.fox.platform.channel.dom.ent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * JsonObject for  Shards
 * @author hector.hidalgo
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"total", "successful","failed"})
public class Shards {

	@JsonProperty(value="total")
	private Integer total;
	
	@JsonProperty(value="successful")
	private Integer successful;
	
	@JsonProperty(value="failed")
	private Integer failed;

}
