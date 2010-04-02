package com.puresol.utils;

import java.util.ArrayList;
import java.util.Properties;

public class PropertyHandler extends Properties {

	private static final long serialVersionUID = -4034865595470135085L;

	private final ArrayList<Property> supportedProperties = new ArrayList<Property>();

	public PropertyHandler() {
		super();
	}

	public PropertyHandler(Properties defaults) {
		super(defaults);
	}

	public void registerProperties(ArrayList<Property> properties) {
		for (Property property : properties) {
			registerProperty(property);
		}
	}

	public void registerProperty(Property property) {
		supportedProperties.add(property);
		if (!containsKey(property.getName())) {
			setProperty(property.getName(), property.getDefaultValue());
		}
	}

	public ArrayList<Property> getProperties() {
		return supportedProperties;
	}

	public void setProperty(Class<?> clazz, String key, String value) {
		setProperty(clazz.getName() + "." + key, value);
	}

	public String getProperty(Class<?> clazz, String key) {
		return getProperty(clazz.getName() + "." + key);
	}
}
