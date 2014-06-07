package com.puresoltechnologies.purifinity.server.common.plugins;

import java.io.Serializable;
import java.util.Properties;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;

/**
 * This class implements some common functionality for all classes which want to
 * register plugin services.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractPluginRegistration {

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
	 *            interface of type {@link PluginService} to be used for
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
	protected <Information extends Serializable, RemoteService extends PluginService<Information>> void register(
			Class<? extends RemoteService> remoteService,
			String remoteServiceJNDIName, String serviceJNDIName,
			Information information) {
		try {
			logger.info("Try to register '" + getName() + "'...");
			InitialContext context = new InitialContext();
			try {
				/*
				 * Retries are implemented, because during startup and parallel
				 * deployment the remote plugin registration might not be
				 * deployed and started, yet.
				 */
				int retried = 0;
				boolean registered = false;
				while (retried < DEFAULT_RETRY_COUNT) {
					try {
						@SuppressWarnings("unchecked")
						RemoteService registrator = (RemoteService) context
								.lookup(remoteServiceJNDIName);
						registrator.registerService(serviceJNDIName,
								information, new Properties());
						registered = true;
						break;
					} catch (NamingException | IllegalStateException e) {
						logger.debug("'"
								+ getName()
								+ "' was not registered, yet. Plugin service was not found.");
						Thread.sleep(DEFAULT_SLEEP);
						retried++;
					}
				}
				if (!registered) {
					logger.error("'"
							+ getName()
							+ "' was not registered. Plugin service was not found.");
					throw new IllegalStateException(
							"Could not register plugin.");
				}
				logger.info("'" + getName() + "' was registered.");
			} finally {
				context.close();
			}
		} catch (InterruptedException | NamingException e) {
			throw new RuntimeException("Could not register service '"
					+ getName() + "'.", e);
		}
	}

	/**
	 * This method is used to register a service from a plugin.
	 * 
	 * @param remoteService
	 *            is an interface {@link Class} which implements a remote
	 *            interface of type {@link PluginService} to be used for
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
	protected <Information extends Serializable, RemoteService extends PluginService<Information>> void unregister(
			Class<? extends RemoteService> remoteService,
			String remoteServiceJNDIName, String serviceJNDIName) {
		try {
			logger.info("Try to unregister '" + getName() + "'...");
			InitialContext context = new InitialContext();
			try {
				try {
					/*
					 * Here is no retry implemented. Because of shutdown, the
					 * remote registration might be shut down already.
					 */
					@SuppressWarnings("unchecked")
					RemoteService registrator = (RemoteService) context
							.lookup(remoteServiceJNDIName);
					registrator.unregisterService(serviceJNDIName);
					logger.info("'" + getName() + "' was unregisted.");
				} catch (NamingException e) {
					logger.info("'"
							+ getName()
							+ "' could not be unregistered. Plugin service was not found.");
				}
			} finally {
				context.close();
			}
		} catch (NamingException e) {
			throw new RuntimeException("Could not unregister service '"
					+ getName() + "'.", e);
		} catch (Throwable e) {
			logger.warn("Could not unregister service '" + getName() + "'.", e);
		}
	}

}
