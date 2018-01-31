package com.fox.platform.channel.dom.ent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import lombok.Getter;
import lombok.Setter;


/**
 * JsonObject for Domain
 * @author hector.hidalgo
 *
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "id", "name", "languageId", "networkId", "countryId" })
public class Domain {

	@JsonProperty(value = "id")
	private Integer id;
	
	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value = "languageId")
	private String languageId;
	
	@JsonProperty(value = "networkId")
	private String networkId;
	
	@JsonProperty(value = "countryId")
	private String countryId;

}
