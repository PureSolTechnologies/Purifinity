package com.puresoltechnologies.purifinity.server.ui.settings;

import com.puresoltechnologies.commons.math.Parameter;

public class RepositorySetting {

	private final String name;
	private final Parameter<?> parameter;
	private String value;

	public RepositorySetting(String name, Parameter<?> parameter) {
		super();
		this.name = name;
		this.parameter = parameter;
	}

	public String getName() {
		return name;
	}

	public Parameter<?> getParameter() {
		return parameter;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
