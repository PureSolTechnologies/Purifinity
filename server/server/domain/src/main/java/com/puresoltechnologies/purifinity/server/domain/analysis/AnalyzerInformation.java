package com.puresoltechnologies.purifinity.server.domain.analysis;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

import com.puresoltechnologies.commons.misc.Version;

public class AnalyzerInformation implements Serializable {

	private static final long serialVersionUID = -1867488685418681896L;

	private final String name;
	private final Version version;
	private final String jndiName;
	private final String description;

	public AnalyzerInformation(@JsonProperty("name") String name,
			@JsonProperty("version") Version version,
			@JsonProperty("jndiName") String jndiName,
			@JsonProperty("description") String description) {
		super();
		this.name = name;
		this.version = version;
		this.jndiName = jndiName;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public Version getVersion() {
		return version;
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
