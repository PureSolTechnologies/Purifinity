/***************************************************************************
 *
 *   GetterAndSetterTester.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.testing;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.swingx.data.RandomNumbers;
import javax.swingx.data.TypeWrapper;

import org.apache.log4j.Logger;

import com.puresol.introspect.IntrospectionUtilities;

public class GetterAndSetterTester {

    private static final Logger logger =
	    Logger.getLogger(GetterAndSetterTester.class);

    public static boolean test(Class<?> clazz) {
	return new GetterAndSetterTester(clazz).test();
    }

    private Class<?> clazz = null;
    private Field[] fields = null;

    private GetterAndSetterTester(Class<?> clazz) {
	this.clazz = clazz;
    }

    private boolean test() {
	readAllFields();
	if (!checkForGettersAndSetters()) {
	    return false;
	}
	if (!checkWithValues()) {
	    return false;
	}
	return true;
    }

    private void readAllFields() {
	fields = clazz.getDeclaredFields();
    }

    private boolean checkForGettersAndSetters() {
	for (Field field : fields) {
	    int modifier = field.getModifiers();
	    if (Modifier.isFinal(modifier)) {
		continue;
	    }
	    if (!checkNaming(field)) {
		return false;
	    }
	    if (!checkGetter(field)) {
		return false;
	    }
	    if (!checkSetter(field)) {
		return false;
	    }
	}
	return true;
    }

    private boolean checkNaming(Field field) {
	String name = field.getName();
	if (name.toLowerCase().getBytes()[0] != name.getBytes()[0]) {
	    logger.error("In class " + clazz.getName() + " the field "
		    + name + " does not start with a lower case letter!");
	    return false;
	}
	return true;
    }

    private boolean checkGetter(Field field) {
	Method getter;
	try {
	    getter = IntrospectionUtilities.getGetter(clazz, field);
	    if (!getter.getReturnType().equals(field.getType())) {
		logger.error("In class " + clazz.getName() + " the field "
			+ field.getName()
			+ " has an invalid getter method!");
		return false;
	    }
	    return true;
	} catch (SecurityException e) {
	    logger.error(e.getMessage(), e);
	} catch (NoSuchMethodException e) {
	    logger.error("In class " + clazz.getName() + " the field "
		    + field.getName() + " does not have a getter method!");
	}
	return false;
    }

    private boolean checkSetter(Field field) {
	Method setter;
	try {
	    setter = IntrospectionUtilities.getSetter(clazz, field);
	    if (!setter.getReturnType().equals(void.class)) {
		logger.error("In class " + clazz.getName() + " the field "
			+ field.getName()
			+ " has an invalid setter method!");
		return false;
	    }
	    return true;
	} catch (SecurityException e) {
	    logger.error(e.getMessage(), e);
	} catch (NoSuchMethodException e) {
	    logger.error("In class " + clazz.getName() + " the field "
		    + field.getName() + " does not have a setter method!");
	}
	return false;
    }

    private boolean checkWithValues() {
	try {
	    Constructor<?> constructor = clazz.getConstructor();
	    Object object = constructor.newInstance();
	    return checkValues(object);
	} catch (SecurityException e) {
	    logger.error(e.getMessage(), e);
	} catch (NoSuchMethodException e) {
	    logger
		    .warn("Cannot check correct internal assignment of getters and setters due to missing default constructor!");
	    return true;
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage(), e);
	} catch (InstantiationException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	} catch (InvocationTargetException e) {
	    logger.error(e.getMessage(), e);
	}
	return false;
    }

    private boolean checkValues(Object object) {
	try {
	    RandomNumbers generator = new RandomNumbers();
	    for (Field field : fields) {
		int modifier = field.getModifiers();
		if (Modifier.isFinal(modifier)) {
		    // do not check final modifiers... ;-)
		    continue;
		}
		if (field.getAnnotation(NoGetterAndSetterValueTest.class) != null) {
		    /*
		     * do not proceed, field is marked as no getter and
		     * setter safe!
		     */
		    continue;
		}
		logger.info(field.getName());
		logger.info(field.getType().getName());
		Method setter =
			IntrospectionUtilities.getSetter(clazz, field);
		logger.info(setter.getName());
		Object value = generator.getRandomValue(field.getType());
		logger.info(value);
		if (field.getType().isPrimitive()) {
		    Class<?> wrapper =
			    TypeWrapper
				    .toPrimitiveWrapper(field.getType());
		    setter.invoke(object, wrapper.cast(value));
		} else {
		    setter.invoke(object, field.getType().cast(value));
		}
		Method getter =
			IntrospectionUtilities.getGetter(clazz, field);
		logger.info(getter.getName());
		Object returnedValue = getter.invoke(object);
		logger.info(returnedValue);
		if (field.getType().isPrimitive()) {
		    logger.info("primitive");
		    Class<?> wrapper =
			    TypeWrapper
				    .toPrimitiveWrapper(field.getType());
		    if (value == wrapper.cast(returnedValue)) {
			logger.error("Getter and setter of field "
				+ field.getName() + " in class "
				+ object.getClass().getName()
				+ " do not work proberly!");
			return false;
		    }
		} else {
		    logger.info("non-primitive");
		    if (value == null) {
			if (value != returnedValue) {
			    return false;
			}
		    } else if (!value.equals(returnedValue)) {
			logger.error("Getter and setter of field "
				+ field.getName() + " in class "
				+ object.getClass().getName()
				+ " do not work proberly!");
			return false;
		    }
		}
	    }
	    return true;
	} catch (SecurityException e) {
	    logger.error(e.getMessage(), e);
	} catch (NoSuchMethodException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	} catch (InvocationTargetException e) {
	    logger.error(e.getMessage(), e);
	}
	return false;
    }
}
