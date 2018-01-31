package com.fox.platform.channel.dom.ent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

/**
 * JsonObject for Type 
 * @author hector.hidalgo
 *
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "description"})
public class Type {
	
	 @JsonProperty(value = "description")
	 private String description;

}
