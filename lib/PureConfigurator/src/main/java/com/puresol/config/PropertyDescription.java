package com.puresol.config;

public interface PropertyDescription<T> {

	public String getPropertyName();

	public String getDisplayName();

	public String getDescription();

	public Class<T> getType();

	public T getDefaultValue();

}
