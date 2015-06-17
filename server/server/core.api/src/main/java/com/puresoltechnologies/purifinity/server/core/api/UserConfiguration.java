package com.puresoltechnologies.purifinity.server.core.api;

import java.util.List;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;

public interface UserConfiguration {

	/**
	 * This method returns the parameters which support a global default for all
	 * users.
	 * 
	 * @return A {@link List} of {@link ConfigurationParameter} is returned.
	 */
	public List<ConfigurationParameter<?>> getDefaultParameters();

}
