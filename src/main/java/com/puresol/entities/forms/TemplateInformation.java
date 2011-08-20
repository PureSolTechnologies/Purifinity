/***************************************************************************
 *
 *   TemplateInformation.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.entities.forms;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import javax.persistence.IdClass;

import org.apache.log4j.Logger;

import com.puresol.exceptions.StrangeSituationException;

/**
 * This class reads all information within an entity bean and creates all
 * relevant information t generate forms afterwards. It's therefore realized a
 * general access to entity beans and some special access methods are
 * implemented to access the database also in a Java SE environment without an
 * active persistence context. This is realized by watching the data access and
 * the current environment. If it's not a Java EE environment the data is fetch
 * separately.
 * 
 * Entities which are used with this class should be persistent or must not have
 * lazy fetched lists.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TemplateInformation implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(TemplateInformation.class);

	private Object entity = null;
	private Hashtable<Integer, TemplateElement> elements = null;

	static public TemplateInformation from(Object entity) {
		return new TemplateInformation(entity);
	}

	private TemplateInformation(Object entity) {
		this.entity = entity;
		elements = new Hashtable<Integer, TemplateElement>();
		generateInformation();
	}

	private void generateInformation() {
		try {
			Class<?> clazz = entity.getClass();
			Method[] methods = clazz.getMethods();
			for (int index = 0; index < methods.length; index++) {
				Method getter = methods[index];
				if (!getter.getName().startsWith("get")) {
					continue;
				}
				if (getter.getName().equals("getClass")) {
					continue;
				}
				Class<?> type = getter.getReturnType();

				Method setter = clazz.getMethod(getter.getName().replaceFirst(
						"get", "set"), type);
				TemplateElement element = TemplateElement.from(entity, getter,
						setter);
				elements.put(element.getId(), element);
			}
		} catch (SecurityException e) {
			throw new StrangeSituationException(e);
		} catch (NoSuchMethodException e) {
			throw new StrangeSituationException(e);
		} catch (IllegalArgumentException e) {
			throw new StrangeSituationException(e);
		}
	}

	public Object getEntity() {
		return entity;
	}

	public int getInputCount() {
		return elements.size();
	}

	public TemplateElement get(int index) {
		return elements.get(index);
	}

	public TemplateElement get(String id) {
		for (int index = 0; index < elements.size(); index++) {
			TemplateElement element = elements.get(index);
			if (id.equals(element.getIdString())) {
				return element;
			}
		}
		return null;
	}

	public Object getPrimaryKey() {
		if (entity.getClass().getAnnotation(IdClass.class) == null) {
			return getSingleID();
		} else {
			logger.trace("Found @IdClass!");
			return getIDClass();
		}
	}

	private Object getSingleID() {
		for (int index = 0; index < elements.size(); index++) {
			TemplateElement element = elements.get(index);
			if (element.isID()) {
				return element.getValue();
			}
		}
		return null;
	}

	private Object getIDClass() {
		try {
			Method createIDClass = entity.getClass().getMethod("createIdClass");
			return createIDClass.invoke(entity);
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
		return null;
	}

	public String getName() {
		Class<?> clazz = entity.getClass();
		Template name = (Template) clazz.getAnnotation(Template.class);
		if (name != null) {
			return name.name();
		}
		return clazz.getSimpleName();
	}

	public void print() {
		System.out.println("Template: " + getName());
		for (int index = 0; index < elements.size(); index++) {
			elements.get(index).print();
		}
	}
}
