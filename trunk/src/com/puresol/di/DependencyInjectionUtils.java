package com.puresol.di;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DependencyInjectionUtils {

    private static boolean injectByField(Object object, Field field,
	    Object value) {
	try {
	    field.set(object, value);
	    return true;
	} catch (Exception e) {
	    // this exception might happen...
	    return false;
	}
    }

    private static boolean injectBySetter(Object object, Field field,
	    Object value) {
	try {
	    String setterName =
		    "set" + field.getName().substring(0, 1).toUpperCase()
			    + field.getName().substring(1);
	    Method setter =
		    object.getClass().getMethod(setterName,
			    value.getClass());
	    if (setter != null) {
		setter.invoke(object, value);
	    }
	    return true;
	} catch (Exception e) {
	    // this might happen...
	    return false;
	}
    }

    public static boolean inject(Class<?> clazz, Object object,
	    Class<? extends Annotation> annotation, Object value) {
	boolean injected = false;
	// dependency injection...
	for (Field field : clazz.getFields()) {
	    if (field.getAnnotation(annotation) != null) {
		if (injectByField(object, field, value)) {
		    injected = true;
		    continue;
		}
	    }
	}
	if (injected) {
	    return injected;
	}
	for (Field field : clazz.getDeclaredFields()) {
	    if (field.getAnnotation(annotation) != null) {
		if (injectBySetter(object, field, value)) {
		    injected = true;
		    continue;
		}
	    }
	}
	return injected;
    }
}
