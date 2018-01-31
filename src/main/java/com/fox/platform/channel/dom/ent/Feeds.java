package com.fox.platform.channel.dom.ent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;


/**
 * JsonObject for Feeds
 * @author hector.hidalgo
 *
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "id", "countryId" })
public class Feeds {

	@JsonProperty(value = "id")
	private String id;
	
	@JsonProperty(value = "countryId")
	private String countryId;

}
