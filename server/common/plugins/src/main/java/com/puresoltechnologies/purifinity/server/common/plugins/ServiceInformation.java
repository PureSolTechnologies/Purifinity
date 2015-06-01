package com.puresoltechnologies.purifinity.server.common.plugins;

import java.io.Serializable;
import java.util.List;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;

/**
 * This interface defines a minimum of functionality all Services need to
 * provide to the {@link ServiceManager}.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface ServiceInformation extends Serializable {

	/**
	 * This method returns a list of all configuration parameters of the
	 * implementing class. This list of parameters is used to setup the specific
	 * behavior of the class.
	 * 
	 * @return A {@link List} of {@link ConfigurationParameter} is returned
	 *         specifying the parameters for configuration.
	 */
	public List<ConfigurationParameter<?>> getConfigurationParameters();

	/**
	 * Returns the service's URL path suffix of the URL to reach the services
	 * UI. This may be <code>null</code> to signal, that the service has no UI.
	 * 
	 * @return A {@link String} is returned containing the URL suffix.
	 */
	public String getServiceURLPath();

	/**
	 * This method returns the service's project URL path of the URL to reach
	 * the services UI for a defined project. This may be <code>null</code> to
	 * signal, that the service has no UI. The URL needs to support the
	 * parameter 'project' which takes a UUID of the project to be shown.
	 * 
	 * @return A {@link String} is returned containing the URL suffix.
	 */
	public String getProjectURLPath();

	/**
	 * This method returns the service's URL path of the URL to reach the
	 * services UI for a defined project run. This may be <code>null</code> to
	 * signal, that the service has no UI. The URL needs to support the
	 * parameter 'project' which takes a UUID of the project to be shown and
	 * also a parameter 'run' which takes the run's UUID.
	 * 
	 * @return A {@link String} is returned containing the URL suffix.
	 */
	public String getRunURLPath();
}
