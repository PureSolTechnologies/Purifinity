/***************************************************************************
 *
 *   CloneTester.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.testing;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.utils.IntrospectionUtilities;

public class CloneTester {

    private static final Logger logger = LoggerFactory
	    .getLogger(CloneTester.class);

    public static boolean test(Object object) {
	return new CloneTester(object).test();
    }

    private Object object = null;
    private Object clonedObject = null;
    private Method cloneMethod = null;
    private Field[] fields = null;
    private Hashtable<Field, Object> savedValues = null;

    private CloneTester(Object object) {
	this.object = object;
    }

    private boolean test() {
	if (!lookForCloneMethod()) {
	    return false;
	}
	if (!readAllFields()) {
	    return false;
	}
	if (!saveAllValues()) {
	    return false;
	}
	if (!cloneObject()) {
	    return false;
	}
	if (!checkObject()) {
	    return false;
	}
	if (!checkAllValues()) {
	    return false;
	}
	return true;
    }

    private boolean lookForCloneMethod() {
	try {
	    cloneMethod = object.getClass().getMethod("clone");

	    if (cloneMethod == null) {
		logger.error("Class " + object.getClass().getName()
			+ " does not have a method called clone()!");
		return false;
	    }
	    return true;
	} catch (SecurityException e) {
	    logger.error(e.getMessage(), e);
	} catch (NoSuchMethodException e) {
	    logger.error(e.getMessage(), e);
	}
	return false;
    }

    private boolean readAllFields() {
	fields = object.getClass().getDeclaredFields();
	return true;
    }

    private boolean saveAllValues() {
	try {
	    savedValues = new Hashtable<Field, Object>();
	    for (Field field : fields) {
		int modifiers = field.getModifiers();
		if (Modifier.isPublic(modifiers)) {
		    savedValues.put(field, field.get(object));
		} else {
		    try {
			Method getter = IntrospectionUtilities.getGetter(
				object, field);
			Object value = getter.invoke(object);
			savedValues.put(field, value);
		    } catch (SecurityException e) {
			logger.warn("Value for field "
				+ field.getName()
				+ " cannot be saved due to privicy and security setting for getter method!");
			continue;
		    } catch (NoSuchMethodException e) {
			logger.warn("Value for field "
				+ field.getName()
				+ " cannot be saved due to privicy and absence of getter method!");
			continue;
		    } catch (InvocationTargetException e) {
			logger.warn("Value for field "
				+ field.getName()
				+ " cannot be saved due to privicy and trouble invokating the getter method!");
			continue;
		    }
		}
	    }
	    return true;
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	}
	return false;
    }

    public boolean cloneObject() {
	try {
	    clonedObject = object.getClass().getMethod("clone").invoke(object);
	    return true;
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage(), e);
	} catch (SecurityException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	} catch (InvocationTargetException e) {
	    logger.error(e.getMessage(), e);
	} catch (NoSuchMethodException e) {
	    logger.error(e.getMessage(), e);
	}
	return false;
    }

    public boolean checkObject() {
	if (clonedObject == object) {
	    logger.error("Cloning was not successful! The reference was copied and original object and cloned object are the same!");
	    return false;
	}
	if (!clonedObject.getClass().equals(object.getClass())) {
	    logger.error("Cloned class " + clonedObject.getClass().getName()
		    + " is not same like original object "
		    + object.getClass().getName() + "!");
	    return false;
	}
	return true;
    }

    public boolean checkAllValues() {
	try {
	    for (Field field : fields) {
		int modifiers = field.getModifiers();
		Object originalValue = savedValues.get(field);
		Object clonedValue;
		if (Modifier.isPublic(modifiers)) {
		    clonedValue = field.get(clonedObject);
		} else {
		    try {
			Method getter = IntrospectionUtilities.getGetter(
				object, field);
			clonedValue = getter.invoke(clonedObject);
		    } catch (SecurityException e) {
			logger.warn("Value for field "
				+ field.getName()
				+ " cannot be saved due to privicy and security setting for getter method!");
			continue;
		    } catch (NoSuchMethodException e) {
			logger.warn("Value for field "
				+ field.getName()
				+ " cannot be saved due to privicy and absence of getter method!");
			continue;
		    } catch (InvocationTargetException e) {
			logger.warn("Value for field "
				+ field.getName()
				+ " cannot be saved due to privicy and trouble invokating the getter method!");
			continue;
		    }
		}
		if (!originalValue.equals(clonedValue)) {
		    logger.error("Cloning was not successful for field "
			    + field.getName() + " due to different values: "
			    + originalValue.toString() + "!="
			    + clonedValue.toString());
		    return false;
		}
		if (originalValue == clonedValue) {
		    logger.error("Cloning was not successful for field "
			    + field.getName()
			    + "! It's the same reference, so it was not cloned!");
		    return false;
		}
	    }
	    return true;
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	}
	return false;
    }
}
