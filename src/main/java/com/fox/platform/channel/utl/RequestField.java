package com.fox.platform.channel.utl;

public enum RequestField {
	COUNTRY("country","AR"),
	FROM("from","0"),
	SIZE("size","300"),
	COMPETITION_ID("middleware-id",null),
	COMPETITION_NAME("competitionName",null),
	REGION("region","es"),
	STREAM_IDS("streamIds",null),
	SIGNAL_IDS("signalIds",null);
	
	
	private String name;
	private String defaultValue;
	
	RequestField(String fieldName, String defaultValue) {
		this.name = fieldName;
		this.defaultValue = defaultValue;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
}
