package com.fox.platform.channel.dom.ent;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * JsonObject for Groups
 * @author hector.hidalgo
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "id", "enabled", "priority", "feeds", "fields" })
public class Groups {

	@JsonProperty(value = "id")
	private String id;
	
	@JsonProperty(value = "enabled")
	private Boolean enabled;
	
	@JsonProperty(value = "priority")
	private String priority;
	
	@JsonProperty(value = "feeds")
	private List<Feeds> feeds;
	
	@JsonProperty(value = "fields")
	private Fields fields;

}
