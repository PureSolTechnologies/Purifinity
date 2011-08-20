package com.puresol.config.sources;

import com.puresol.config.ConfigurationSource;

public abstract class AbstractConfigurationSource implements
		ConfigurationSource {

	private final String name;

	public AbstractConfigurationSource(String name) {
		super();
		this.name = name;
	}

	@Override
	public final String getName() {
		return name;
	}

}
