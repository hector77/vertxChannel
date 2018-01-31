package com.fox.platform.channel.dom.ent;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

/**
 * JsonObject for Source 
 * @author hector.hidalgo
 *
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "id", "code", "name", "publishDate", "lastUpdate", "isSocialPost", "type", "domain", "show","updatedOn","groups","feeds","categories" })
public class Source {

	@JsonProperty(value = "id")
	private String id;

	@JsonProperty(value = "code")
	private String code;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "publishDate")
	private String publishDate;

	@JsonProperty(value = "lastupdate")
	private String lastUpdate;

	@JsonProperty(value = "isSocialPost")
	private Boolean isSocialPost;

	@JsonProperty(value = "type")
	private Type type;

	@JsonProperty(value = "domain")
	private Domain domain;

	@JsonProperty(value = "show")
	private Show show;

	@JsonProperty(value = "updatedOn")
	private String updatedOn;
	
	@JsonProperty(value = "groups")
	private List<Groups> groups;
	
	@JsonProperty(value = "feeds")
	private List<Feeds> feeds;
	
	@JsonProperty(value = "categories")
	private List<Categories> categories;

}
