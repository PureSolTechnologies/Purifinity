package com.puresol.config.properties;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.puresol.utils.EnumUtilities;

/**
 * This class is the central manager for all systems configuration. The
 * configuration is stored in properties which are assigned to different
 * contexts.
 * 
 * There might be different named instances which are used for different
 * purposes like plugin configuration, project configuration and system
 * configuration.
 * 
 * <b>This class is thread safe.</b>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ConfigurationManager {

	private static final ConcurrentMap<ConfigurationLayer, ConfigurationManager> instance = new ConcurrentHashMap<ConfigurationLayer, ConfigurationManager>();

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
			ConfigurationLayer configurationLayer) {
		if (!instance.containsKey(configurationLayer)) {
			createInstance(configurationLayer);
		}
		return instance.get(configurationLayer);
	}

	/**
	 * This method acutally creates the instance named with name.
	 * 
	 * @param name
	 */
	private static synchronized void createInstance(
			ConfigurationLayer configurationLayer) {
		if (!instance.containsKey(configurationLayer)) {
			instance.put(configurationLayer, new ConfigurationManager());
		}
	}

	/**
	 * This method looks for a configuration within a specified context. The
	 * method searches all layers of configuration in a specified order.
	 * 
	 * The order is:
	 * 
	 * <pre>
	 *     1) PROJECT
	 *     2) PLUGINS
	 *     3) SYSTEM
	 *     4) default value from PropertyDescription
	 * </pre>
	 * 
	 * The search is inverse to the layers. As soon as there is a value for the
	 * property found, the value is returned. This is needed to be fast. If
	 * there is no setting found, the default value of PropertyDescription is
	 * returned. It's assured than that no NULL is returned.
	 * 
	 * The method is safe for changes in ConfigurationType as soon as the
	 * constants in the ConfigurationType enum are sorted correctly.
	 * 
	 * @param context
	 *            is the context to be searched in.
	 * @param description
	 *            is the PropertyDescription of the property to be found.
	 * @return The settings value is returned.
	 */
	public static String getProperty(String context,
			PropertyDescription<?> description) {
		Object layers[] = ConfigurationLayer.class.getEnumConstants();
		for (int id = layers.length - 1; id >= 0; id--) {
			ConfigurationLayer layer = (ConfigurationLayer) layers[id];
			ConfigurationManager manager = ConfigurationManager
					.getInstance(layer);
			Properties properties = manager.getContextProperties(context);
			if (properties != null) {
				String value = properties.getProperty(description
						.getPropertyName());
				if ((value != null) && (!value.isEmpty())) {
					return value;
				}
			}
		}
		return description.getDefaultValue().toString();
	}

	public static byte getByte(String context,
			PropertyDescription<?> description) {
		return Byte.valueOf(getProperty(context, description));
	}

	public static int getInteger(String context,
			PropertyDescription<?> description) {
		return Integer.valueOf(getProperty(context, description));
	}

	public static long getLong(String context,
			PropertyDescription<?> description) {
		return Long.valueOf(getProperty(context, description));
	}

	public static float getFloat(String context,
			PropertyDescription<?> description) {
		return Float.valueOf(getProperty(context, description));
	}

	public static double getDouble(String context,
			PropertyDescription<?> description) {
		return Double.valueOf(getProperty(context, description));
	}

	public static boolean getBoolean(String context,
			PropertyDescription<?> description) {
		return Boolean.valueOf(getProperty(context, description));
	}

	public static <T> T getEnumConstant(String context,
			PropertyDescription<?> description, Class<T> enumeration) {
		return EnumUtilities.findEnumConstante(enumeration,
				getProperty(context, description));
	}

	private final ConcurrentMap<String, Properties> contextProperties = new ConcurrentHashMap<String, Properties>();

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

	private void addContext(String context) {
		contextProperties.putIfAbsent(context, new Properties());
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
