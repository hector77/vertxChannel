package com.fox.platform.channel.dom.ent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;


/**
 * JsonObject for Paradigm 
 * @author hector.hidalgo
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "id", "synopsis", "languageId" })
public class Paradigm {

	@JsonProperty(value = "id")
	private String id;

	@JsonProperty(value = "synopsis")
	private String synopsis;

	@JsonProperty(value = "languageId")
	private String languageId;

}
