package com.puresol.osgi;

import java.util.List;

import com.puresol.config.ConfigurationSource;
import com.puresol.config.PropertyDescription;

/**
 * This interface is used to register bundle configuration facilities. Each
 * bundle might register an interface implementation of this type to use the
 * central configuration dialog to set and reset values.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface BundleConfigurator {

	public String getName();

	public String getPathName();

	public List<PropertyDescription<?>> getPropertyDescriptions();

	public ConfigurationSource getSource();

}
