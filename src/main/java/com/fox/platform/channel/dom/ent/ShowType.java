package com.fox.platform.channel.dom.ent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * JsonObject for ShowType 
 * @author hector.hidalgo
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "id", "name", "showGroupId"})
public class ShowType {

	@JsonProperty(value = "id")
	private String id;
	
	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value = "showGroupId")
	private String showGroupId;

}
