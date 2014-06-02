package com.puresoltechnologies.purifinity.server.domain.evaluation;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class EvaluatorInformation implements Serializable {

	private static final long serialVersionUID = -1867488685418681896L;

	private final String name;
	private final String jndiName;
	private final String description;

	public EvaluatorInformation(@JsonProperty("name") String name,
			@JsonProperty("jndiName") String jndiName,
			@JsonProperty("description") String description) {
		super();
		this.name = name;
		this.jndiName = jndiName;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getJndiName() {
		return jndiName;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return getName() + " " + "(" + getJndiName() + ")";
	}
}
