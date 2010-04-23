package com.puresol.utils.di;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

/**
 * This class provides all methods used for dependency injection as static class
 * members. This class is used as depencency injection facility by
 * DIClassBuilder.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DependencyInjection {

    private static final Logger logger = Logger
	    .getLogger(DependencyInjection.class);

    /**
     * This method injects all dependency into an object provided.
     * 
     * @param <T>
     *            is the class type of the client.
     * @param client
     *            is the object where all injections are going to.
     * @param injections
     *            are the injects used.
     * @return A reference to the client is returned for chaining.
     */
    public static <T> T inject(T client, Injection... injections) {
	Class<?> clazz = client.getClass();
	do {
	    inject(clazz, client, injections);
	    clazz = clazz.getSuperclass();
	} while (clazz != null);
	return client;
    }

    private static <T> T inject(Class<?> clazz, T client,
	    Injection... injections) {
	Field[] fields = clazz.getDeclaredFields();
	for (Field field : fields) {
	    Inject inject = field.getAnnotation(Inject.class);
	    if (inject != null) {
		inject(field, inject, client, injections);
	    }
	}
	return client;
    }

    private static void inject(Field field, Inject inject, Object client,
	    Injection... injections) {
	for (Injection injection : injections) {
	    doInjection(field, inject, client, injection);
	}
    }

    private static void doInjection(Field field, Inject inject, Object client,
	    Injection injection) {
	try {
	    if (injection == null) {
		return;
	    }
	    Class<?> fieldType = field.getType();
	    if (!injection.getObject().getClass().equals(fieldType)) {
		return;
	    }
	    if (!inject.value().equals(injection.getName())) {
		return;
	    }
	    field.setAccessible(true);
	    field.set(client, injection.getObject());
	    field.setAccessible(false);
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	}
    }
}
