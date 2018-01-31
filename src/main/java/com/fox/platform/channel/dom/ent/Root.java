package com.fox.platform.channel.dom.ent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

/**
 * JsonObject for Root Object
 * @author hector.hidalgo
 *
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"took", "timedOut","shards","hits"})
public class Root {
	
	@JsonProperty(value="took")
	private Integer took;

    @JsonProperty(value="timed_out")
    private Boolean timedOut;

    @JsonProperty(value="_shards")
    private Shards shards;
    
    @JsonProperty(value="hits")
    private Hits hits;

}
