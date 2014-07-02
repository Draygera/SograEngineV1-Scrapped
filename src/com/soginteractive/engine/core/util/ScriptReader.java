package com.soginteractive.engine.core.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.soginteractive.engine.core.AbstractScripter;

public class ScriptReader extends AbstractScripter {

	private String stream;

	public ScriptReader(String path) {
		this(Gdx.files.internal(path));
	}

	public ScriptReader(FileHandle handle) {
		super(handle);
	}

	public void readScriptFile() {
		stream = handle.readString();
	}

	public String getStream() {
		return stream;
	}

}
