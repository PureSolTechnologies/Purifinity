package com.puresoltechnologies.purifinity.server.wildfly.utils;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;

/**
 * This class contains functionality used to handle JNDI lookups, instance
 * creation and JNDI name creation.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public class JndiUtils {

    private static final Logger logger = LoggerFactory.getLogger(JndiUtils.class);

    private static final int DEFAULT_RETRY_COUNT = 12;
    private static final int DEFAULT_SLEEP = 5000;

    public static <T> T createRemoteEJBInstance(Class<T> clazz, String jndiName) {
	try {
	    /*
	     * Retries are implemented, because during startup and parallel
	     * deployment the remote plugin registration might not be deployed
	     * and started, yet.
	     */
	    int retried = 0;
	    while (true) {
		try {
		    @SuppressWarnings("unchecked")
		    T remoteEJB = (T) InitialContext.doLookup(jndiName);
		    return remoteEJB;
		} catch (NamingException | IllegalStateException e) {
		    retried++;
		    if (retried < DEFAULT_RETRY_COUNT) {
			logger.info("Could not lookup object '" + jndiName + "', yet.");
			Thread.sleep(DEFAULT_SLEEP);
		    } else {
			logger.error("'" + jndiName + "' was was not found.");
			throw new IllegalStateException("Could not find '" + jndiName + "'.");
		    }
		}
	    }
	} catch (InterruptedException e) {
	    throw new RuntimeException("Could not find '" + jndiName + "' due to caught interrupt.", e);
	}
    }

    /**
     * This method creates a new JNDI name for a remote interface.
     * 
     * @param earName
     * @param ejbName
     * @param interfaceClass
     * @param implementationClass
     * @return
     */
    public static <Interface> String createGlobalName(String earName, String ejbName, Class<Interface> interfaceClass,
	    Class<? extends Interface> implementationClass) {
	if (!interfaceClass.isInterface()) {
	    throw new IllegalArgumentException(
		    "Class '" + interfaceClass.getName() + "' is not an interface as expected.");
	}
	if (implementationClass.isInterface()) {
	    throw new IllegalArgumentException(
		    "Class '" + implementationClass.getName() + "' is not a class as expected.");
	}
	if (!interfaceClass.isAssignableFrom(implementationClass)) {
	    throw new IllegalArgumentException("Class '" + implementationClass.getName()
		    + "' is not implementing interface '" + interfaceClass.getName() + "' as expected.");
	}
	return "java:global/" + earName + "/" + ejbName + "-" + BuildInformation.getVersion() + "/"
		+ implementationClass.getSimpleName() + "!" + interfaceClass.getName();
    }

}
