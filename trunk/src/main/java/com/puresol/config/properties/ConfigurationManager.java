package com.puresol.config.properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * This class is the central manager for all systems configuration. The
 * configuration is stored in properties which are assigned to different
 * contexts.
 * 
 * There might be different named instances which are used for different
 * purposes like plugin configuration, project configuration and system
 * configuration.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ConfigurationManager {

	private static final ConcurrentMap<ConfigurationType, ConfigurationManager> instance = new ConcurrentHashMap<ConfigurationType, ConfigurationManager>();

	/**
	 * This method returns the instance named by name. If there is not instance
	 * with that name available, a new instance is created and returned. If an
	 * instance is available, that instance is returned.
	 * 
	 * @param name
	 *            is the name of the instance to be returned.
	 * @return
	 */
	public static ConfigurationManager getInstance(
			ConfigurationType configurationType) {
		if (!instance.containsKey(configurationType)) {
			createInstance(configurationType);
		}
		return instance.get(configurationType);
	}

	/**
	 * This method acutally creates the instance named with name.
	 * 
	 * @param name
	 */
	private static synchronized void createInstance(
			ConfigurationType configurationType) {
		if (!instance.containsKey(configurationType)) {
			instance.put(configurationType, new ConfigurationManager());
		}
	}

	private final Map<String, Properties> contextProperties = new HashMap<String, Properties>();

	/**
	 * This constructor is private to make this class private to have singleton
	 * instances.
	 */
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
