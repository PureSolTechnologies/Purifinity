package com.puresoltechnologies.purifinity.server.domain.analysis;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

import com.puresoltechnologies.commons.misc.Version;

public class AnalyzerServiceInformation implements Serializable {

    private static final long serialVersionUID = -1867488685418681896L;

    private final String name;
    private final String version;
    private final Version pluginVersion;
    private final String jndiName;
    private final String description;

    public AnalyzerServiceInformation(@JsonProperty("name") String name,
	    @JsonProperty("version") String version,
	    @JsonProperty("pluginVersion") Version pluginVersion,
	    @JsonProperty("jndiName") String jndiName,
	    @JsonProperty("description") String description) {
	super();
	this.name = name;
	this.version = version;
	this.pluginVersion = pluginVersion;
	this.jndiName = jndiName;
	this.description = description;
    }

    public String getName() {
	return name;
    }

    public String getVersion() {
	return version;
    }

    public Version getPluginVersion() {
	return pluginVersion;
    }

    public String getJndiName() {
	return jndiName;
    }

    public String getDescription() {
	return description;
    }

    @Override
    public String toString() {
	return getName() + " " + getVersion() + "(" + getJndiName() + ")";
    }
}
