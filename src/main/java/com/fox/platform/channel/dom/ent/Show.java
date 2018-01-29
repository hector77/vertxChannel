package com.fox.platform.channel.dom.ent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import lombok.Data;

/**
 * JsonObject for Show 
 * @author hector.hidalgo
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "id", "name", "code", "publishDate", "lastUpdate", "programmingSerie","showType","paradigm" })
public class Show {

	@JsonProperty(value = "id")
	private String id;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "code")
	private String code;

	@JsonProperty(value = "publishDate")
	private String publishDate;

	@JsonProperty(value = "lastupdate")
	private String lastUpdate;

	@JsonProperty(value = "programmingSerie")
	private String programmingSerie;

	@JsonProperty(value = "type")
	private ShowType showType;
	
	@JsonProperty(value = "paradigm")
	private Paradigm paradigm;

}
