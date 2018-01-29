package com.fox.platform.channel.dom.ent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * JsonObject for Fields
 * @author hector.hidalgo
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fields {
	@JsonProperty(value="Thumbnail")
    private String thumbnail;

    @JsonProperty(value="Image")
    private String image1;

    @JsonProperty(value="EpisodeId")
    private String episodeId;

    @JsonProperty(value="Image620x240")
    private String image620x2401;

    @JsonProperty(value="Shortname")
    private String shortName1;

    @JsonProperty(value="ProductionId")
    private String productionId;

    @JsonProperty(value="Image145x105")
    private String image145x105;

    @JsonProperty(value="Description")
    private String description;

    @JsonProperty(value="tuneInDate")
    private String tuneInDate;

    @JsonProperty(value="tuneInHour")
    private String tuneInHour;

    @JsonProperty(value="eventDuration")
    private String eventDuration;

    @JsonProperty(value="statisticsId")
    private String statisticsId;

    @JsonProperty(value="header-image")
    private String headerImage;

    @JsonProperty(value="validation")
    private String validation;

    @JsonProperty(value="metakeywords")
    private String metakeywords;

    
    @JsonProperty(value="eventName")
    private String eventName;

    @JsonProperty(value="embed_code")
    private String embedCode;

    @JsonProperty(value="theme")
    private String theme;

    
    @JsonProperty(value="hashtag")
    private String hashtag;

    @JsonProperty(value="main-image")
    private String mainImage;

    @JsonProperty(value="auth_level_cl")
    private String authLevelCl;

    @JsonProperty(value="competitionName")
    private String competitionName;

    @JsonProperty(value=" @JsonProperty(value=\"competitionName\")")
    private String sport;

    @JsonProperty(value="signal")
    private String signal;

    @JsonProperty(value="auth_level")
    private String authLevel;

    @JsonProperty(value="eventDate")
    private String eventDate;

    @JsonProperty(value="deck")
    private String deck;

    @JsonProperty(value="streams_deprecated")
    private String streamsDeprecated;

    @JsonProperty(value="channel")
    private String channel;

    @JsonProperty(value="picture")
    private String picture;

    @JsonProperty(value="stats_id")
    private String statsId;

    @JsonProperty(value="picture_national")
    private String pictureNational;

    @JsonProperty(value="datafactory_id")
    private String datafactoryId;

    @JsonProperty(value="full_name")
    private String fullName;

    @JsonProperty(value="playlist-description")
    private String playlistDescription;

    @JsonProperty(value="thumb")
    private String thumb;

    @JsonProperty(value="image")
    private String image;

    @JsonProperty(value="short-name")
    private String shortName;

    @JsonProperty(value="image200x200")
    private String image200x200;

    @JsonProperty(value="mrss")
    private String mrss;

    @JsonProperty(value="tags")
    private String tags;

    @JsonProperty(value="Character")
    private String character;

    @JsonProperty(value="Actor")
    private String actor;

    @JsonProperty(value="name")
    private String name;

    @JsonProperty(value="vendor")
    private String vendor;

    @JsonProperty(value="url")
    private String url;

    @JsonProperty(value="match_type")
    private String matchType;

    @JsonProperty(value="team_b")
    private String teamB;

    @JsonProperty(value="competition")
    private String competition;

    @JsonProperty(value="poll")
    private String poll;

    @JsonProperty(value="date")
    private String date;

    @JsonProperty(value="team_a")
    private String teamA;

    @JsonProperty(value="time")
    private String time;

    @JsonProperty(value="unbundled")
    private String unbundled;

    @JsonProperty(value="image620x240")
    private String image620x240;

    @JsonProperty(value="eventEnd")
    private String eventEnd;

    @JsonProperty(value="intellicore_id")
    private String intellicoreId;

    @JsonProperty(value="programmingSystem")
    private String programmingSystem;

    @JsonProperty(value="logo")
    private String logo;


}
