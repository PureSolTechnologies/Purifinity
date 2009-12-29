/***************************************************************************
 *
 *   ApplicationServerFactory.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.appserv;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;

import com.puresol.exceptions.StrangeSituationException;

/**
 * This factory class is meant to create to needed contexts for application
 * servers used within the software. It is possible to have later on different
 * kinds of application servers.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ApplicationServerFactory {

	static public int JBOSS = 0;

	static private Hashtable<Integer, ApplicationServerContext> appServerContext = new Hashtable<Integer, ApplicationServerContext>();

	static public ApplicationServerContext createContext(int serverType,
			String host, int jnpPort)
			throws ApplicationServerNotSupportedException {
		if (serverType == JBOSS) {
			if (appServerContext.get(serverType) == null) {
				createAppServerContext(serverType, host, jnpPort);
			}
			return appServerContext.get(serverType);
		} else {
			throw new ApplicationServerNotSupportedException(serverType);
		}
	}

	static private synchronized void createAppServerContext(int serverType,
			String host, int jnpPort)
			throws ApplicationServerNotSupportedException {
		if (appServerContext.get(serverType) == null) {
			try {
				Properties p = new Properties();
				if (serverType == JBOSS) {
					p.put(Context.INITIAL_CONTEXT_FACTORY,
							"org.jnp.interfaces.NamingContextFactory");
					p.put(Context.URL_PKG_PREFIXES,
							"org.jboss.naming:org.jnp.interfaces");
					p
							.put(Context.PROVIDER_URL, "jnp://" + host + ":"
									+ jnpPort);
				} else {
					throw new ApplicationServerNotSupportedException(serverType);
				}
				appServerContext.put(serverType,
						new ApplicationServerContext(p));
			} catch (NamingException e) {
				throw new StrangeSituationException(e);
			}
		}
	}

}
