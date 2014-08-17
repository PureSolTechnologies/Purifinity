package com.puresoltechnologies.purifinity.server.ui.system;

import java.io.Serializable;

/**
 * This class represents a single Environment Variable retrieved by
 * {@link System#getenv()}.
 * 
 * @author Rick-Rainer Ludwig
 */
public class EnvironmentVariable implements Serializable {

	private static final long serialVersionUID = -5323502192984107264L;

	private final String name;
	private final String value;

	public EnvironmentVariable(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

}
