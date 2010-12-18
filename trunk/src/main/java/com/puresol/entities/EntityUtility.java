/***************************************************************************
 *
 *   EntityUtility.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.entities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.swingx.data.Time;

import org.apache.log4j.Logger;

import com.puresol.entities.forms.AutomaticContent;
import com.puresol.entities.forms.AutomaticContentType;
import com.puresol.entities.forms.IllegalEntityException;

public class EntityUtility {

	private static Logger logger = Logger.getLogger(EntityUtility.class);

	private String user;

	public EntityUtility(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public void setAutomatics(Object entity) throws IllegalEntityException {
		try {
			logger.debug("set automatics for class" + entity.getClass());
			Method[] methods = entity.getClass().getMethods();
			for (int index = 0; index < methods.length; index++) {
				Method method = methods[index];
				AutomaticContent automatics = (AutomaticContent) method
						.getAnnotation(AutomaticContent.class);
				if (automatics == null) {
					continue;
				}
				logger.debug("set automatic content for " + entity.getClass()
						+ ":" + method.getName());
				if (!method.getName().startsWith("get")) {
					throw new IllegalEntityException(entity);
				}
				Object value = method.invoke(entity);
				if (isValueSet(value) && (!automatics.overwrite())) {
					continue;
				}
				if (automatics.type() == AutomaticContentType.CURRENT_DATE) {
					setCurrentDate(entity, method, value);
				} else if (automatics.type() == AutomaticContentType.CURRENT_USER) {
					setCurrentUser(entity, method, value);
				}
			}
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalEntityException(entity);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalEntityException(entity);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalEntityException(entity);
		} catch (SecurityException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalEntityException(entity);
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalEntityException(entity);
		}
	}

	private boolean isValueSet(Object value) {
		if (value == null) {
			return false;
		}
		if (value.getClass().equals(String.class)) {
			if (((String) value).isEmpty()) {
				return false;
			}
		}
		return true;
	}

	private void setCurrentDate(Object entity, Method getter, Object value)
			throws IllegalEntityException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		if (value != null) {
			if (!value.getClass().equals(Date.class)) {
				throw new IllegalEntityException(entity);
			}
		}
		Method setter = entity.getClass().getMethod(
				getter.getName().replaceFirst("get", "set"), Date.class);
		if (setter == null) {
			throw new IllegalEntityException(entity);
		}
		setter.invoke(entity, Time.now());
	}

	private void setCurrentUser(Object entity, Method getter, Object value)
			throws IllegalEntityException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		if (value != null) {
			if (!value.getClass().equals(String.class)) {
				throw new IllegalEntityException(entity);
			}
		}
		Method setter = entity.getClass().getMethod(
				getter.getName().replaceFirst("get", "set"), String.class);
		if (setter == null) {
			throw new IllegalEntityException(entity);
		}
		setter.invoke(entity, getUser());
	}
}
