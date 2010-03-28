package com.puresol.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

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
	@SuppressWarnings("unchecked")
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
			return (R) method.invoke(null, params);
		} catch (Throwable e) {
			logger.error(e);
		}
		throw new MethodInvokationException(clazz, methodName, returnType,
				parameterClasses);
	}
}
