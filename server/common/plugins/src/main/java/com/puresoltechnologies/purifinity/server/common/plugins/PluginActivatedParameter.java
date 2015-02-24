package com.puresoltechnologies.purifinity.server.common.plugins;

import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.commons.math.LevelOfMeasurement;

public class PluginActivatedParameter extends ConfigurationParameter<Boolean> {

    public PluginActivatedParameter() {
	super("activated", "", LevelOfMeasurement.NOMINAL,
		"Specifies whether the plugin is activated or not.",
		Boolean.class, "plugin.activated", false);
    }

}
