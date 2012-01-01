package com.puresol.config.sources;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.puresol.config.ConfigurationSource;

/**
 * This configuration source is a collection for sources. The source added here
 * should come from OSGi bundles which register their sources here.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BundleConfigurators extends AbstractConfigurationSource {

    private static final BundleConfigurators instance = new BundleConfigurators(
	    "Plugin Configurations");

    private final List<ConfigurationSource> sources = new Vector<ConfigurationSource>();

    public static BundleConfigurators getInstance() {
	return instance;
    }

    private BundleConfigurators(String name) {
	super(name);
    }

    public void addSource(ConfigurationSource source) {
	sources.add(source);
    }

    public void removeSource(ConfigurationSource source) {
	sources.remove(source);
    }

    @Override
    public String getProperty(String key) {
	for (ConfigurationSource source : sources) {
	    String value = source.getProperty(key);
	    if (value != null) {
		return value;
	    }
	}
	return null;
    }

    @Override
    public boolean isChangeable() {
	return true;
    }

    @Override
    public boolean isOverridable() {
	return false;
    }

    @Override
    public void setProperty(String key, String value) {
	if (value != null) {
	    for (ConfigurationSource source : sources) {
		if (source.getProperty(key) != null) {
		    source.setProperty(key, value);
		}
	    }
	}
    }

    @Override
    public void save() throws IOException {
	for (ConfigurationSource source : sources) {
	    if (source.isChangeable()) {
		source.save();
	    }
	}
    }

}
