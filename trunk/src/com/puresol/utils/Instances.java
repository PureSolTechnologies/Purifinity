package com.puresol.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * This class is the implementation for utilities to handle different instances
 * of different classes. This class can be used a simple factory or a singleton
 * manager. For the Flyweight Pattern this class can be the manager for the fly
 * weighted classes.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Instances {

    private static final Logger logger = Logger.getLogger(Instances.class);

    private static Class<?>[] params2Classes(Object[] params) {
	Class<?>[] classes = new Class<?>[params.length];
	for (int index = 0; index < params.length; index++)
	    classes[index] = params[index].getClass();
	return classes;
    }

    /**
     * This class creates a new instance of a class.
     * 
     * @param <C>
     *            is the class to be intatiated.
     * @param clazz
     *            is the class to be intatiated.
     * @param params
     *            is an array of parameters to be passed to the constructor.
     * @return A new instance of the specified class is returned.
     * @throws ClassInstantiationException
     *             is thrown in any exceptional event.
     */
    public static <C> C createInstance(Class<C> clazz, Object... params)
	    throws ClassInstantiationException {
	Class<?>[] parameterClasses = params2Classes(params);
	if (clazz == null) {
	    throw new ClassInstantiationException("null", parameterClasses);
	}
	try {
	    Constructor<C> constructor = clazz.getConstructor(parameterClasses);
	    return constructor.newInstance(params);
	} catch (Throwable e) {
	    logger.error(e.getMessage(), e);
	}
	throw new ClassInstantiationException(clazz, parameterClasses);
    }

    /**
     * This method runs a static method within a specified class.
     * 
     * @param <C>
     *            is the class to run.
     * @param <R>
     *            is the return value to be expected.
     * @param clazz
     *            is the class to be run of type C.
     * @param methodName
     *            is the name of the static method to be invoked.
     * @param returnType
     *            is the expected return type.
     * @param params
     *            is an array of parameters to be passed to the method.
     * @return A return value is returned in case of there is one.
     * @throws MethodInvokationException
     *             is thrown in any exception event.
     */
    public static <C, R> R runStaticMethod(Class<C> clazz, String methodName,
	    Class<R> returnType, Object... params)
	    throws MethodInvokationException {
	Class<?>[] parameterClasses = params2Classes(params);
	try {
	    Method method = clazz.getMethod(methodName, parameterClasses);
	    if (!method.getReturnType().equals(returnType)) {
		throw new NoSuchMethodException("Return type "
			+ method.getReturnType().getName() + " is invalid for "
			+ returnType.getName() + "!");
	    }
	    @SuppressWarnings("unchecked")
	    R r = (R) method.invoke(null, params);
	    return r;
	} catch (Throwable e) {
	    logger.error(e);
	}
	throw new MethodInvokationException(clazz, methodName, returnType,
		parameterClasses);
    }

    /**
     * This method creates classes for a complete list of Class<?>.
     * 
     * @param <C>
     * @param clazz
     * @param classes
     * @param params
     * @return
     * @throws ClassInstantiationException
     */
    public static <C> List<C> createInstanceList(Class<C> clazz,
	    List<Class<? extends C>> classes, Object... params)
	    throws ClassInstantiationException {
	List<C> list = new ArrayList<C>();
	for (Class<? extends C> c : classes) {
	    list.add(createInstance(c, params));
	}
	return list;
    }
}
