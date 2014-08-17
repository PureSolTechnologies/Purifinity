package com.puresoltechnologies.purifinity.server.ui.system;

import java.io.Serializable;

/**
 * This class represents a single System Property retrieved from
 * {@link System#getProperties()}.
 * 
 * @author Rick-Rainer Ludwig
 */
public class SystemProperty implements Serializable {

	private static final long serialVersionUID = 3815337417181528010L;

	private final String name;
	private final String value;

	public SystemProperty(String name, String value) {
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
