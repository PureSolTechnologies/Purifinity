package com.puresol.config;

public interface ConfigurationSource {

	public String getName();

	public boolean isOverridable();

	public boolean isChangeable();

	public String getProperty(String key);

	public void setProperty(String key, String value);

}
