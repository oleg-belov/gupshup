package com.stackroute.gupshup.activityproducer.domain;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public final class Create implements Activity{

	private final String context;
	private final String type;
	private final String summary;
	private final ASObject actor;
	private final ASObject object;

	@JsonCreator
	public Create(
			@JsonProperty("@context") String context,
			@JsonProperty("type") String type,
			@JsonProperty("summary") String summary,
			@JsonDeserialize(as=Person.class) @JsonProperty("actor") ASObject actor,
			@JsonDeserialize(as=Note.class) @JsonProperty("object") ASObject object) {
		this.context = context;
		this.type = type;
		this.summary = summary;
		this.object = object;
		this.actor = actor;
	}

	public String getContext() {
		return context;
	}

	public String getType() {
		return type;
	}

	public String getSummary() {
		return summary;
	}

	public ASObject getActor() {
		return actor;
	}

	public ASObject getObject() {
		return object;
	}

}
