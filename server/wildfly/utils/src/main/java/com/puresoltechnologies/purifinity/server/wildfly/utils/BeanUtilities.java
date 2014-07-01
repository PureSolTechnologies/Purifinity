package com.puresoltechnologies.purifinity.server.wildfly.utils;

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

/**
 * This class contains utilities for bean lookup and bean handling.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class BeanUtilities {

    private static BeanManager beanManager;

    /**
     * Private default constructor to avoid instantiation.
     */
    private BeanUtilities() {
    }

    /**
     * Looks a bean to a given type up in JNDI and returns a reference to an
     * instance.
     * 
     * @param type
     *            is the type of the bean to look up.
     * @param <T>
     *            is the generic type of the bean.
     * @return A managed bean of the specified type is returned.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> type) {
	if (beanManager == null) {
	    getBeanManager();
	}

	Set<Bean<?>> beans = beanManager.getBeans(type);

	if (beans.size() > 1) {
	    throw new RuntimeException(
		    "Ambiguous bean references found for type " + type);
	}
	Bean<T> bean = (Bean<T>) beans.iterator().next();
	CreationalContext<T> creationalContext = beanManager
		.createCreationalContext(bean);
	return (T) beanManager.getReference(bean, type, creationalContext);
    }

    /**
     * Looks a {@link BeanManager} up in JNDI and sets the static field to its
     * value for later use.
     */
    private static synchronized void getBeanManager() {
	if (beanManager == null) {
	    beanManager = JndiUtils.createRemoteEJBInstance(BeanManager.class,
		    "java:comp/BeanManager");
	}
    }
}