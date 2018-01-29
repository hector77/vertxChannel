package com.fox.platform.channel.dom.ent;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import lombok.Data;

/**
 * JsonObject for Hit
 * @author hector.hidalgo
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "index", "type","id","score","source","sort"})
public class Hit {
	
	 @JsonProperty(value = "_index")
	  private String index;

	  @JsonProperty(value = "_type")
	  private String type;

	  @JsonProperty(value = "_id")
	  private String id;

	  @JsonProperty(value = "_score")
	  private Integer score;

	 @JsonProperty(value = "_source")
	  private Source source;
	  
	  @JsonProperty(value = "sort")
	  private List<Sort> sort;

}
