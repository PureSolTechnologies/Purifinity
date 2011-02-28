package com.puresol.config.sources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationInJar extends AbstractConfigurationSource {

	private final Properties properties = new Properties();
	private final boolean overridable;

	public ConfigurationInJar(String name, Class<?> anchorClass,
			String resourceName, boolean overrideable) throws IOException {
		super(name);
		this.overridable = overrideable;
		InputStream inStream = anchorClass.getResourceAsStream(resourceName);
		properties.load(inStream);
	}

	@Override
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	@Override
	public boolean isChangeable() {
		return false;
	}

	@Override
	public boolean isOverridable() {
		return overridable;
	}

	@Override
	public void setProperty(String key, String value) {
		throw new IllegalStateException(
				"This configuration resource is not changeable!");
	}

}
