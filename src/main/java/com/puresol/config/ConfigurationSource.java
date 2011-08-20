package com.puresol.config;

import java.io.IOException;

public interface ConfigurationSource {

	public String getName();

	public boolean isOverridable();

	public boolean isChangeable();

	public String getProperty(String key);

	public void setProperty(String key, String value);

	public void save() throws IOException;
}
