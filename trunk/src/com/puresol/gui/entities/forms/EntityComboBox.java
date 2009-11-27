package com.puresol.gui.entities.forms;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.swingx.ComboBox;

import com.puresol.appserv.EntityManagerExtension;
import com.puresol.appserv.EntityManagerFactories;
import com.puresol.exceptions.StrangeSituationException;

public class EntityComboBox extends ComboBox {

	private static final long serialVersionUID = 1L;
	private Object entity = null;
	private List<?> entityList = null;

	/**
	 * Create EntityComboBox without initial value.
	 * 
	 * @param entity
	 *            is wheiter a Class<> type or a reference of an entity.
	 */
	static public EntityComboBox from(Object entity) {
		return new EntityComboBox(entity);
	}

	private EntityComboBox(Object entity) {
		super();
		try {
			if (entity.getClass().equals(Class.class)) {
				this.entity = ((Class<?>) entity).getConstructor()
						.newInstance();
			} else {
				this.entity = entity;
			}
			Class<?> clazz = this.entity.getClass();
			if (clazz.getAnnotation(Entity.class) == null) {
				throw new StrangeSituationException("Class +" + clazz.getName()
						+ " is not a legal entity!");
			}
			readAndFillEntries();
		} catch (IllegalArgumentException e) {
			throw new StrangeSituationException(e);
		} catch (SecurityException e) {
			throw new StrangeSituationException(e);
		} catch (InstantiationException e) {
			throw new StrangeSituationException(e);
		} catch (IllegalAccessException e) {
			throw new StrangeSituationException(e);
		} catch (InvocationTargetException e) {
			throw new StrangeSituationException(e);
		} catch (NoSuchMethodException e) {
			throw new StrangeSituationException(e);
		}
	}

	private void readAndFillEntries() {
		EntityManager entityManager = EntityManagerFactories.create(entity)
				.createEntityManager();
		EntityManagerExtension extension = new EntityManagerExtension(
				entityManager);
		entityList = extension.getAll(entity.getClass());
		Iterator<?> iterator = entityList.iterator();
		while (iterator.hasNext()) {
			Object value = iterator.next();
			this.addItem(value.toString());
		}
		extension = null;
		entityManager.close();
	}

	public Object getSelectedEntity() {
		Object o = entityList.get(getSelectedIndex());
		return o;
	}
}
