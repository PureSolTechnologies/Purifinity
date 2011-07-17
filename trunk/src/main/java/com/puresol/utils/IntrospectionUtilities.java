/***************************************************************************
 *
 *   IntrospectionUtilities.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * This class contains helper functions for efficient code introspection like
 * name wrappers from fields to getters and setters.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IntrospectionUtilities {

	public static String getGetterName(Field field) {
		String name = field.getName();
		if (field.getType().equals(boolean.class)) {
			return "is" + name.substring(0, 1).toUpperCase(Locale.US)
					+ name.substring(1);
		} else {
			return "get" + name.substring(0, 1).toUpperCase(Locale.US)
					+ name.substring(1);
		}
	}

	public static String getSetterName(Field field) {
		String name = field.getName();
		return "set" + name.substring(0, 1).toUpperCase(Locale.US)
				+ name.substring(1);
	}

	public static Method getGetter(Object object, Field field)
			throws SecurityException, NoSuchMethodException {
		return getGetter(object.getClass(), field);
	}

	public static Method getGetter(Class<?> clazz, Field field)
			throws SecurityException, NoSuchMethodException {
		Method getter = clazz.getMethod(getGetterName(field));
		if (!getter.getReturnType().equals(field.getType())) {
			throw new NoSuchMethodException();
		}
		return getter;
	}

	public static Method getSetter(Object object, Field field)
			throws SecurityException, NoSuchMethodException {
		return getSetter(object.getClass(), field);
	}

	public static Method getSetter(Class<?> clazz, Field field)
			throws SecurityException, NoSuchMethodException {
		Method setter = clazz.getMethod(getSetterName(field), field.getType());
		if (!setter.getReturnType().equals(void.class)) {
			throw new NoSuchMethodException();
		}
		return setter;
	}

	public static void setField(Object object, String name, Object value)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = object.getClass();
		Field field = clazz.getDeclaredField(name);
		boolean accessable = field.isAccessible();
		if (!accessable)
			field.setAccessible(true);
		field.set(object, value);
		if (!accessable)
			field.setAccessible(false);
	}

	public static Object getField(Object object, String name)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = object.getClass();
		Field field = clazz.getDeclaredField(name);
		boolean accessable = field.isAccessible();
		if (!accessable)
			field.setAccessible(true);
		Object value = field.get(object);
		if (!accessable)
			field.setAccessible(false);
		return value;
	}

	public static Object invokeMethod(Object object, String name,
			Object... arguments) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Class<?> clazz = object.getClass();
		Class<?> argumentClasses[] = new Class<?>[arguments.length];
		for (int argumentId = 0; argumentId < arguments.length; argumentId++) {
			argumentClasses[argumentId] = arguments[argumentId].getClass();
		}
		Method field = clazz.getDeclaredMethod(name, argumentClasses);
		boolean accessable = field.isAccessible();
		if (!accessable)
			field.setAccessible(true);
		Object value = field.invoke(object, arguments);
		if (!accessable)
			field.setAccessible(false);
		return value;
	}
}
