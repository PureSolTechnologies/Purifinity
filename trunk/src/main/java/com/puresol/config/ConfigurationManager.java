package com.puresol.config;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConfigurationManager implements Configuration {

	private final ConcurrentMap<String, ConfigurationSource> sources = new ConcurrentHashMap<String, ConfigurationSource>();

	public ConfigurationManager() {

	}

	public void addSource(ConfigurationSource source) {
		String name = source.getName();
		if (sources.containsKey(name)) {
			throw new IllegalStateException("A source with name '" + name
					+ "' is already defined! (" + sources.get(name).toString()
					+ ")");
		}
		sources.put(source.getName(), source);
	}

	public void removeSource(ConfigurationSource source) {
		sources.remove(source);
	}

	@Override
	public String getProperty(String key, String defaultValue) {
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

	public byte getByte(String key, byte defaultValue) {
		try {
			return Byte.valueOf(getProperty(key, null));
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	public short getShort(String key, short defaultValue) {
		try {
			return Short.valueOf(getProperty(key, null));
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	public int getInteger(String key, int defaultValue) {
		try {
			return Integer.valueOf(getProperty(key, null));
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	public long getLong(String key, long defaultValue) {
		try {
			return Long.valueOf(getProperty(key, null));
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		try {
			return Boolean.valueOf(getProperty(key, null));
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	public <T> T getEnum(String key, Class<T> enumClass, T defaultValue) {
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

	public Set<String> getSourceNames() {
		return sources.keySet();
	}

	public void setProperty(String name, String key, String value) {
		ConfigurationSource source = sources.get(name);
		if (source == null) {
			throw new IllegalStateException("Configuration source '" + name
					+ "' is not defined.");
		}
		source.setProperty(key, value);
	}

	public void save() throws IOException {
		for (String sourceName : sources.keySet()) {
			ConfigurationSource source = sources.get(sourceName);
			if (source.isChangeable()) {
				source.save();
			}
		}
	}
}
