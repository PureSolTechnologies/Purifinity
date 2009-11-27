package com.puresol.appserv;

import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.log4j.Logger;

/**
 * This extended context is used to add methods for creating beans.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ApplicationServerContext extends InitialContext {

	private static final Logger logger = Logger
			.getLogger(ApplicationServerContext.class);

	public ApplicationServerContext() throws NamingException {
		super();
	}

	public ApplicationServerContext(boolean lazy) throws NamingException {
		super(lazy);
	}

	public ApplicationServerContext(Hashtable<?, ?> environment)
			throws NamingException {
		super(environment);
	}

	public Object createRemoteBean(String name, Class<?> cast)
			throws BeanNotAvailableException {
		try {
			return PortableRemoteObject.narrow(lookup(name + "/remote"), cast);
		} catch (NamingException e) {
			logger.error(e.getMessage(), e);
			throw new BeanNotAvailableException(name, cast);
		}

	}

	public Object createLocalBean(String name, Class<?> cast)
			throws BeanNotAvailableException {
		try {
			return PortableRemoteObject.narrow(lookup(name + "/local"), cast);
		} catch (NamingException e) {
			logger.error(e.getMessage(), e);
			throw new BeanNotAvailableException(name, cast);
		}

	}
}
