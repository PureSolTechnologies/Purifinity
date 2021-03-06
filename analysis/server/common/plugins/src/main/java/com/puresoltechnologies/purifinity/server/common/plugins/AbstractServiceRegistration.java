package com.puresoltechnologies.purifinity.server.common.plugins;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

/**
 * This class implements some common functionality for all classes which want to
 * register plugin services.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractServiceRegistration<ServiceInfo extends ServiceInformation> {

    @Inject
    private ServiceManagerProxy serviceManagerProxy;

    @Inject
    private Logger logger;

    /**
     * Returns the name of the service.
     * 
     * @return A {@link String} with the name is returned.
     */
    protected abstract String getName();

    /**
     * Returns the {@link ServiceInformation} object of the service.
     * 
     * @return A {@link ServiceInfo} object is returned.
     */
    public abstract ServiceInfo getServiceInformation();

    /**
     * This method writes the service registration into the database.
     */
    protected abstract void registerInDatabase();

    /**
     * This method removes the service registration from the database.
     */
    protected abstract void unregisterInDatabase();

    /**
     * This method is used to register a service from a plugin.
     * 
     * @param remoteService
     *            is an interface {@link Class} which implements a remote
     *            interface of type {@link ServiceManager} to be used for
     *            registration.
     * @param remoteServiceJNDIName
     *            is the JNDI name of the remote service for registration.
     * @param pluginInformation
     *            is the {@link PluginInformation} to be registered.
     * @param serviceJNDIName
     *            is the JDNI name of the service to be called from the central
     *            register.
     * @param information
     *            is the information about the service to be used for
     *            registration.
     * @param <Information>
     *            is the {@link ServiceInformation} object type.
     * @param <Service>
     *            is the Service to be registered.
     * @param <RemoteService>
     *            is the remote service to be registered.
     */
    protected <Information extends ServiceInformation, Service, RemoteService extends ServiceManager<Information, Service>> void register(
	    Class<? extends RemoteService> remoteService, String remoteServiceJNDIName,
	    PluginInformation pluginInformation, String serviceJNDIName, Information information) {
	if (serviceManagerProxy == null) {
	    throw new RuntimeException("Service manager proxy was not injected!");
	}
	serviceManagerProxy.register(getName(), remoteService, remoteServiceJNDIName, pluginInformation,
		serviceJNDIName, information);
    }

    /**
     * This method is used to register a service from a plugin.
     * 
     * @param remoteService
     *            is an interface {@link Class} which implements a remote
     *            interface of type {@link ServiceManager} to be used for
     *            registration.
     * @param remoteServiceJNDIName
     *            is the JNDI name of the remote service for registration.
     * @param serviceJNDIName
     *            is the JDNI name of the service to be called from the central
     *            register.
     * @param <Information>
     *            is the {@link ServiceInformation} object type.
     * @param <Service>
     *            is the Service to be unregistered.
     * @param <RemoteService>
     *            is the remote service to be unregistered.
     */
    protected <Information extends ServiceInformation, Service, RemoteService extends ServiceManager<Information, Service>> void unregister(
	    Class<? extends RemoteService> remoteService, String remoteServiceJNDIName, String serviceJNDIName) {
	try {
	    logger.info("Try to unregister '" + getName() + "'...");
	    /*
	     * Here is no retry implemented. Because of shutdown, the remote
	     * registration might be shut down already.
	     */
	    RemoteService registrator = JndiUtils.createRemoteEJBInstance(remoteService, remoteServiceJNDIName);
	    registrator.unregisterService(serviceJNDIName);
	    logger.info("'" + getName() + "' was unregisted.");
	} catch (Throwable e) {
	    logger.warn("Could not unregister service '" + getName() + "'.", e);
	}
    }

}
