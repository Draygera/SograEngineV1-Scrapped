package com.soginteractive.engine.core;

import static com.soginteractive.engine.core.util.ScriptUtils.*;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

public class Event1 implements Serializable {

	private String name, description;
	private boolean complete;

	private final String OBJ = "event";
	private final String NAME = "name";
	private final String DESC = "description";

	public Event1() {
		this("", "");
	}

	public Event1(String name, String description) {
		name(name).description(description);
	}

	public Event1 name(String name) {
		this.name = name;
		return this;
	}

	public Event1 description(String description) {
		this.description = description;
		return this;
	}

	public Event1 complete(boolean complete) {
		this.complete = complete;
		return this;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isComplete() {
		return complete;
	}

	@Override
	public void write(Json json) {
		json.writeObjectStart(OBJ);
		{
			json.writeValue(NAME, name);
			json.writeValue(DESC, description);
		}
		json.writeObjectEnd();
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		JsonValue child = jsonData.child;
		name(child.getString(NAME));
		description(child.getString(DESC));
	}

	public void printJson() {
		printString(NAME, name);
		printString(DESC, description);
		System.out.println("\n");
	}

}
