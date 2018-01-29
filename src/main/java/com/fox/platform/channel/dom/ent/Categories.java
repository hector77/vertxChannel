package com.fox.platform.channel.dom.ent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;


/**
 * JsonObject for Categories
 * @author hector.hidalgo
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "category" })
public class Categories {

	@JsonProperty(value = "category")
	private String category;

}
