package com.fox.platform.channel.dom.ent;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

/**
 * JsonObject for Hits
 * @author hector.hidalgo
 *
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "total", "maxScore", "hits" })
public class Hits {

	@JsonProperty(value = "total")
	private Integer total;

	@JsonProperty(value = "max_score")
	private Integer maxScore;

	@JsonProperty(value = "hits")
	private List<Hit> hits;

}
