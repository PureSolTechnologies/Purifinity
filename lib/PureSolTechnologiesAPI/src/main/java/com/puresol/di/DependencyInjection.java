package com.puresol.di;

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
			String injectionName = inject.value();
			if (injectionName.isEmpty()) {
				doInjectionAnonymously(field, client, injection);
			} else {
				doInjectionByName(field, injectionName, client, injection);
			}
		}
	}

	/**
	 * This method performs the injection for a unnamed (anonymous) injection.
	 * The target field must have the exact same class type as the injection.
	 * Null as injection is not(!) allowed.
	 * 
	 * @param field
	 *            is the field to be injected.
	 * @param client
	 * @param injection
	 */
	private static void doInjectionAnonymously(Field field, Object client,
			Injection injection) {
		try {
			if (injection == null) {
				return;
			}
			String name = injection.getName();
			if (!name.isEmpty()) {
				return;
			}
			Object value = injection.getValue();
			if (value == null) {
				return;
			}
			if (!field.getType().equals(value.getClass())) {
				return;
			}
			field.setAccessible(true);
			field.set(client, value);
			field.setAccessible(false);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * The injection is performed in cases the name of the injection is
	 * specified. The target field must have an assignable class type. Null is
	 * allowed to be injected.
	 * 
	 * @param field
	 *            is the field to be injected.
	 * @param injectionName
	 *            is the Inject annotation name for the injection.
	 * @param client
	 * @param injection
	 */
	private static void doInjectionByName(Field field, String injectionName,
			Object client, Injection injection) {
		try {
			if (injection == null) {
				return;
			}
			String name = injection.getName();
			if (name.isEmpty()) {
				return;
			}
			if (!injectionName.equals(name)) {
				return;
			}
			Object value = injection.getValue();
			if (value != null) {
				Class<?> fieldType = field.getType();
				if (!fieldType.isAssignableFrom(value.getClass())) {
					return;
				}
			}
			field.setAccessible(true);
			field.set(client, value);
			field.setAccessible(false);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
