package com.puresoltechnologies.purifinity.server.common.plugins;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

/**
 * This class implements some common functionality for all classes which want to
 * register plugin services.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractServiceRegistration {

	private static final int DEFAULT_RETRY_COUNT = 30;
	private static final int DEFAULT_SLEEP = 1000;

	@Inject
	private Logger logger;

	protected abstract String getName();

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
	protected <Information extends ServiceInformation, RemoteService extends ServiceManager<Information>> void register(
			Class<? extends RemoteService> remoteService,
			String remoteServiceJNDIName, PluginInformation pluginInformation,
			String serviceJNDIName, Information information) {
		try {
			/*
			 * Retries are implemented, because during startup and parallel
			 * deployment the remote plugin registration might not be deployed
			 * and started, yet.
			 */
			logger.info("Try to register '" + getName() + "'...");
			int retried = 0;
			boolean registered = false;
			while (retried < DEFAULT_RETRY_COUNT) {
				try {
					RemoteService registrator = JndiUtils
							.createRemoteEJBInstance(remoteService,
									remoteServiceJNDIName);
					registrator.registerService(pluginInformation,
							serviceJNDIName, information);
					registered = true;
					break;
				} catch (IllegalStateException e) {
					logger.debug("'"
							+ getName()
							+ "' was not registered, yet. Plugin service was not found.");
					Thread.sleep(DEFAULT_SLEEP);
					retried++;
				}
			}
			if (!registered) {
				logger.error("'" + getName()
						+ "' was not registered. Plugin service was not found.");
				throw new IllegalStateException("Could not register plugin.");
			}
			logger.info("'" + getName() + "' was registered.");
		} catch (InterruptedException e) {
			throw new RuntimeException("Could not register service '"
					+ getName() + "'.", e);
		}
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
	 * @throws InterruptedException
	 *             is thrown if the registration was interrupted.
	 * @throws NamingException
	 *             is thrown in case of a JNDI naming issue.
	 * @throws IllegalStateException
	 *             is thrown, if the registration process times out.
	 */
	protected <Information extends ServiceInformation, RemoteService extends ServiceManager<Information>> void unregister(
			Class<? extends RemoteService> remoteService,
			String remoteServiceJNDIName, String serviceJNDIName) {
		try {
			logger.info("Try to unregister '" + getName() + "'...");
			/*
			 * Here is no retry implemented. Because of shutdown, the remote
			 * registration might be shut down already.
			 */
			RemoteService registrator = JndiUtils.createRemoteEJBInstance(
					remoteService, remoteServiceJNDIName);
			registrator.unregisterService(serviceJNDIName);
			logger.info("'" + getName() + "' was unregisted.");
		} catch (Throwable e) {
			logger.warn("Could not unregister service '" + getName() + "'.", e);
		}
	}

}
