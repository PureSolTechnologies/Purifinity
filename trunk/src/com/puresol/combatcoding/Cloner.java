package com.puresol.combatcoding;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

/**
 * This class prvides the functionality to clone objects. The cloning procedure
 * is check internally to avoid wrong clones.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            is the type of the object to be cloned.
 */
public class Cloner<T> {

	private static final Logger logger = Logger.getLogger(Cloner.class);

	private final T source;

	public Cloner(T source) {
		super();
		this.source = source;
	}

	public T getClone() throws CloneNotSupportedException {
		return cloneObject(source);
	}

	private T cloneObject(T source) throws CloneNotSupportedException {
		try {
			if (Cloneable.class.isAssignableFrom(source.getClass())) {
				Class<?> clazz = source.getClass();
				Class<?> superClazz = clazz.getSuperclass();
				Method cloneMethod = superClazz.getDeclaredMethod("clone");
				cloneMethod.setAccessible(true);
				@SuppressWarnings("unchecked")
				T cloned = (T) cloneMethod.invoke(source);
				cloneObject(source, cloned, clazz);
				return cloned;
			}
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
		throw new CloneNotSupportedException();
	}

	private void cloneObject(T o, T cloned, Class<?> clazz)
			throws CloneNotSupportedException {
		try {
			if (clazz == null) {
				return;
			}
			for (Field field : clazz.getDeclaredFields()) {
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				if (field.isEnumConstant()) {
					continue;
				}
				Class<?> fieldType = field.getType();
				if (fieldType.isPrimitive()) {
					field.set(cloned, field.get(o));
					continue;
				}
				if (Cloneable.class.isAssignableFrom(fieldType)) {
					field.set(cloned,
							fieldType.getMethod("clone").invoke(field.get(o)));
				}
				throw new RuntimeException(
						"Not all cloning procedures are implemented yet!");
			}
			cloneObject(o, cloned, clazz.getSuperclass());
			return;
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
		throw new CloneNotSupportedException();
	}
}
