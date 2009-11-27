package com.puresol.entities.forms;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.puresol.exceptions.StrangeSituationException;

/**
 * This class represents a single template element with all its properties and
 * attributes.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TemplateElement implements Serializable {

	private static final long serialVersionUID = 1L;

	private Object entity;
	private Method getter;
	private Method setter;
	private Object oldValue;

	static public TemplateElement from(Object entity, Method getter,
			Method setter) {
		try {
			return new TemplateElement(entity, getter, setter);
		} catch (IllegalArgumentException e) {
			throw new StrangeSituationException(e);
		} catch (IllegalAccessException e) {
			throw new StrangeSituationException(e);
		} catch (InvocationTargetException e) {
			throw new StrangeSituationException(e);
		}
	}

	private TemplateElement(Object entity, Method getter, Method setter)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		this.entity = entity;
		this.getter = getter;
		this.setter = setter;
		this.oldValue = getter.invoke(entity);
		checkConsistency();
	}

	private void checkConsistency() throws IllegalArgumentException {
		checkEntity();
		checkGetter();
		checkSetter();
		checkTypes();
		checkID();
	}

	private void checkEntity() throws IllegalArgumentException {
		if (entity == null) {
			throw new IllegalArgumentException("Entity must not be null!");
		}
		if (entity.getClass().getAnnotation(Entity.class) == null) {
			throw new IllegalArgumentException("Entity '"
					+ entity.getClass().getName()
					+ "'has not an Entity annotation!");
		}
	}

	private void checkGetter() throws IllegalArgumentException {
		if (getter == null) {
			throw new IllegalArgumentException("Getter method '"
					+ getter.getName() + "' must not be null!");
		}
		if (!getter.getName().startsWith("get")) {
			throw new IllegalArgumentException("Getter method '"
					+ getter.getName() + "' must start with 'get'!");
		}
		if (getter.getParameterTypes().length != 0) {
			throw new IllegalArgumentException("Getter method '"
					+ getter.getName() + "' must not have any parameter!");
		}
	}

	private void checkSetter() throws IllegalArgumentException {
		if (setter == null) {
			throw new IllegalArgumentException("Setter method '"
					+ setter.getName() + "' must not be null!");
		}
		if (!setter.getName().startsWith("set")) {
			throw new IllegalArgumentException("Setter method '"
					+ setter.getName() + "' must start with 'set'!");
		}
		if (setter.getParameterTypes().length != 1) {
			throw new IllegalArgumentException(
					"Setter method must have exact one parameter!");
		}
	}

	private void checkTypes() {
		if (!getter.getReturnType().equals(setter.getParameterTypes()[0])) {
			throw new IllegalArgumentException("Getter '" + getter.getName()
					+ "' and setter '" + setter.getName()
					+ "' do not have same type!");
		}
	}

	private void checkID() {
		String id = getIdString();
		if (!setter.getName().equals("set" + id)) {
			throw new IllegalArgumentException("Getter '" + getter.getName()
					+ "' and setter '" + setter.getName()
					+ "' do not have proper names!");
		}
	}

	public String getIdString() {
		return getter.getName().substring(3);
	}

	public int getId() {
		return ((TemplateElementLayout) getter
				.getAnnotation(TemplateElementLayout.class)).id();
	}

	public String getName() {
		TemplateElementLayout name = getter
				.getAnnotation(TemplateElementLayout.class);
		if (name == null) {
			return getIdString();
		}
		return name.name();
	}

	public boolean isOptional() {
		return ((TemplateElementLayout) getter
				.getAnnotation(TemplateElementLayout.class)).optional();
	}

	public boolean isHidden() {
		return ((TemplateElementLayout) getter
				.getAnnotation(TemplateElementLayout.class)).hidden();
	}

	public boolean isAutomatic() {
		return (getter.getAnnotation(AutomaticContent.class) != null);
	}

	public boolean isPassword() {
		return (getter.getAnnotation(Password.class) != null);
	}

	public boolean isID() {
		return (getter.getAnnotation(Id.class) != null);
	}

	public Object getEntity() {
		return entity;
	}

	public Method getGetter() {
		return getter;
	}

	public Method getSetter() {
		return setter;
	}

	public Class<?> getType() {
		return getter.getReturnType();
	}

	public Class<?>[] getGenerics() {
		Type[] types = ((ParameterizedType) getter.getGenericReturnType())
				.getActualTypeArguments();
		Class<?>[] classes = new Class<?>[types.length];
		for (int index = 0; index < types.length; index++) {
			classes[index] = (Class<?>) types[index];
		}
		return classes;
	}

	public Object getValue() {
		try {
			return getter.invoke(entity);
		} catch (IllegalArgumentException e) {
			throw new StrangeSituationException(e);
		} catch (IllegalAccessException e) {
			throw new StrangeSituationException(e);
		} catch (InvocationTargetException e) {
			throw new StrangeSituationException(e);
		}
	}

	public void setValue(Object object) {
		try {
			setter.invoke(entity, object);
		} catch (IllegalArgumentException e) {
			throw new StrangeSituationException(e);
		} catch (IllegalAccessException e) {
			throw new StrangeSituationException(e);
		} catch (InvocationTargetException e) {
			throw new StrangeSituationException(e);
		}
	}

	public Object getOldValue() {
		return oldValue;
	}

	public void print() {
		System.out.println("Input: " + getName());
		System.out.println("\tEntity: " + getEntity().getClass().getName());
		System.out.println("\tType: " + getType().getName());
		System.out.println("\tGetter: " + getGetter().getName());
		System.out.println("\tSetter: " + getSetter().getName());
		System.out.println("\tValue: " + getValue());
		System.out.println("\tOldValue: " + getOldValue());
	}
}
