package com.puresoltechnologies.purifinity.server.common.plugins;

import java.util.concurrent.TimeUnit;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.NamingException;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Stateless
public class ServiceManagerProxy {

    private static final int MAX_RETRIES = 60;
    private static final int RETRY_DELAY = 10;

    @Inject
    private Logger logger;

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
     * @param information
     *            is the information about the service to be used for
     *            registration.
     * @throws InterruptedException
     *             is thrown if the registration was interrupted.
     * @throws NamingException
     *             is thrown in case of a JNDI naming issue.
     * @throws IllegalStateException
     *             is thrown, if the registration process times out.
     */
    @Lock(LockType.WRITE)
    public <Information extends ServiceInformation, Service, RemoteService extends ServiceManager<Information, Service>> void register(
	    String name, Class<? extends RemoteService> remoteService, String remoteServiceJNDIName,
	    PluginInformation pluginInformation, String serviceJNDIName, Information information) {
	int counter = 0;
	while (counter < MAX_RETRIES) {
	    try {
		logger.info("Try to register '" + name + "'...");
		RemoteService registrator = JndiUtils.createRemoteEJBInstance(remoteService, remoteServiceJNDIName);
		registrator.registerService(pluginInformation, serviceJNDIName, information);
		logger.info("'" + name + "' was registered.");
		return;
	    } catch (IllegalStateException e) {
		logger.info("'" + name + "' could not be registered '" + e.getMessage() + "'.");
		try {
		    TimeUnit.SECONDS.sleep(RETRY_DELAY);
		} catch (InterruptedException e1) {
		    logger.warn("'" + name + "' could not be registered due to interrupt.", e1);
		}
		counter++;
	    }
	}
    }

}
