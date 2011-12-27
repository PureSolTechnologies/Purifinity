package com.puresol.config;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * This is a central manager for configuration information. There is only one
 * instance per running application.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ConfigurationManager implements Configuration {

    private static final ConfigurationManager instance = new ConfigurationManager();

    public static ConfigurationManager getInstance() {
	return instance;
    }

    private final ConcurrentMap<String, ConfigurationSource> sources = new ConcurrentHashMap<String, ConfigurationSource>();

    private ConfigurationManager() {
	super();
    }

    /**
     * This method adds a new configuration source to the manager.
     * 
     * @param source
     */
    public final void addSource(ConfigurationSource source) {
	String name = source.getName();
	if (sources.containsKey(name)) {
	    throw new IllegalStateException("A source with name '" + name
		    + "' is already defined! (" + sources.get(name).toString()
		    + ")");
	}
	sources.put(source.getName(), source);
    }

    /**
     * This method removes a configuration source from the manager.
     * 
     * @param source
     */
    public final void removeSource(ConfigurationSource source) {
	sources.remove(source);
    }

    @Override
    public final String getProperty(String key, String defaultValue) {
	String result = defaultValue;
	for (String sourceName : sources.keySet()) {
	    ConfigurationSource source = sources.get(sourceName);
	    String value = source.getProperty(key);
	    if (value != null) {
		result = value;
		if (!source.isOverridable()) {
		    break;
		}
	    }
	}
	return result;
    }

    public final byte getByte(String key, byte defaultValue) {
	try {
	    return Byte.valueOf(getProperty(key, null));
	} catch (NumberFormatException e) {
	    return defaultValue;
	}
    }

    public final short getShort(String key, short defaultValue) {
	try {
	    return Short.valueOf(getProperty(key, null));
	} catch (NumberFormatException e) {
	    return defaultValue;
	}
    }

    public final int getInteger(String key, int defaultValue) {
	try {
	    return Integer.valueOf(getProperty(key, null));
	} catch (NumberFormatException e) {
	    return defaultValue;
	}
    }

    public final long getLong(String key, long defaultValue) {
	try {
	    return Long.valueOf(getProperty(key, null));
	} catch (NumberFormatException e) {
	    return defaultValue;
	}
    }

    public final boolean getBoolean(String key, boolean defaultValue) {
	try {
	    return Boolean.valueOf(getProperty(key, null));
	} catch (NumberFormatException e) {
	    return defaultValue;
	}
    }

    public final <T> T getEnum(String key, Class<T> enumClass, T defaultValue) {
	try {
	    String value = getProperty(key, null);
	    for (T constant : enumClass.getEnumConstants()) {
		if (constant.toString().equals(value)) {
		    return constant;
		}
	    }
	    return defaultValue;
	} catch (Throwable e) {
	    return defaultValue;
	}
    }

    public final Set<String> getSourceNames() {
	return sources.keySet();
    }

    /**
     * This method sets a new property value.
     * 
     * @param name
     * @param key
     * @param value
     * @throws ConfigurationNotChangeableException
     */
    public final void setProperty(String name, String key, String value)
	    throws ConfigurationNotChangeableException {
	ConfigurationSource source = sources.get(name);
	if (source == null) {
	    throw new IllegalStateException("Configuration source '" + name
		    + "' is not defined.");
	}
	if (source.isChangeable()) {
	    source.setProperty(key, value);
	} else {
	    throw new ConfigurationNotChangeableException(
		    "The configuration for '" + key + "' is not changeable!");
	}
    }

    /**
     * This method saves the changes of the configurations back.
     * 
     * @throws IOException
     */
    public void save() throws IOException {
	for (String sourceName : sources.keySet()) {
	    ConfigurationSource source = sources.get(sourceName);
	    if (source.isChangeable()) {
		source.save();
	    }
	}
    }
}
