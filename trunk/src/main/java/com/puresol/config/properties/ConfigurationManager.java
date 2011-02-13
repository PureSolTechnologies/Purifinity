package com.puresol.config.properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ConfigurationManager {

	private final Map<String, Properties> contextProperties = new HashMap<String, Properties>();

	private static ConfigurationManager instance = null;

	public static ConfigurationManager getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new ConfigurationManager();
		}
	}

	private ConfigurationManager() {
		super();
	}

	public Properties getContextProperties(String context) {
		return contextProperties.get(context);
	}

	public void clear() {
		contextProperties.clear();
	}

	public void addContext(String context) {
		if (!contextProperties.containsKey(context)) {
			contextProperties.put(context, new Properties());
		}
	}

	public void addProperty(String context, String key, String value) {
		addContext(context);
		contextProperties.get(context).put(key, value);
	}

	public void addProperties(String context, Properties properties) {
		addContext(context);
		contextProperties.get(context).putAll(properties);
	}

	public void addPropertyIfNotPresent(String context, String key, String value) {
		addContext(context);
		Properties properties = contextProperties.get(context);
		if (!properties.containsKey(key)) {
			properties.put(key, value);
		}
	}

	public Set<String> getContexts() {
		return contextProperties.keySet();
	}
}
