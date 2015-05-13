package com.puresoltechnologies.purifinity.server.core.api;

import java.util.List;

import javax.inject.Singleton;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;

/**
 * This interface is used to provide the configuration for the Purifinity
 * system.
 * 
 * @author Rick-Rainer Ludwig
 */
@Singleton
public interface PurifinityConfiguration {

	/**
	 * This method returns a list of all configuration parameters.
	 * 
	 * @return A {@link List} of {@link ConfigurationParameter} is returned.
	 */
	public List<ConfigurationParameter<?>> getParameters();

}
