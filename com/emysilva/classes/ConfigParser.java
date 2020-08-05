package com.emysilva.classes;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigParser {

	private String filename;

	private final Map<String, String> env = new LinkedHashMap<>();

	public ConfigParser(String config) {
		this.filename = config;
	}

	public ConfigParser() {
		this.filename = "default-name";
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Map<String, String> getEnv() {
		return env;
	}

	public String get(String key) {
		return env.get(key);
	}
}
