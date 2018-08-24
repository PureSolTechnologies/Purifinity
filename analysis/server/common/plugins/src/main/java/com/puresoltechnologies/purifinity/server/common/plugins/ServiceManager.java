package com.puresoltechnologies.purifinity.server.common.plugins;

import java.util.Collection;

/**
 * This interface defines a service managers which are used for service
 * registrations.
 * 
 * @author Rick-Rainer Ludwig
 *
 * @param <ServiceInfo>
 *            is a {@link ServiceInformation} object which defines information
 *            about the service. This is up to the actual implementation.
 */
public interface ServiceManager<ServiceInfo extends ServiceInformation, ServiceImplementation> {

	/**
	 * This method is used for the registration of a service to the manager.
	 * 
	 * @param pluginInformation
	 *            is a {@link PluginInformation} object keeping information
	 *            about the plugin which provides the service.
	 * @param jndiName
	 *            is the JNDI name where to connect to the service.
	 * @param serviceInformation
	 *            is the information object which keeps the service information.
	 */
	public void registerService(PluginInformation pluginInformation,
			String jndiName, ServiceInfo serviceInformation);

	/**
	 * This method unregisters a service.
	 * 
	 * @param jndiName
	 *            is the JNDI name of the service which is to unregister.
	 */
	public void unregisterService(String jndiName);

	/**
	 * This method checks the manager to have services or not.
	 * 
	 * @return <code>true</code> is returned if services are available.
	 *         <code>false</code> is returned otherwise.
	 */
	public boolean hasServices();

	/**
	 * This method returns a collection of all available services.
	 * 
	 * @return A {@link Collection} is returned containing the service
	 *         information.
	 */
	public Collection<ServiceInfo> getServices();

	/**
	 * This method returns a list of all plugins which provide services which
	 * are registered already.
	 * 
	 * @return A {@link Collection} of {@link PluginInformation} is returned.
	 */
	public Collection<PluginInformation> getPlugins();

	/**
	 * This method creates an instance of a {@link ServiceImplementation} with
	 * the given JNDI name.
	 * 
	 * @param jndi
	 *            is the JNDI name of the service for which a proxy is to be
	 *            created.
	 * @return An {@link ServiceImplementation} compliant proxy is returned.
	 */
	public ServiceImplementation createProxy(String jndi);

	/**
	 * This method creates an instance out of the service id. This service is
	 * actually a proxy via remoting to the plugin.
	 * 
	 * @param serviceId
	 *            is the id of the service.
	 * @return A proxy is for the service returned.
	 */
	public ServiceImplementation getInstanceById(String serviceId);
}
