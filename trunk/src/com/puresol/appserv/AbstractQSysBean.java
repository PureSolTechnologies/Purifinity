package com.puresol.appserv;

import java.util.List;

import com.puresol.entities.EntityUtility;
import com.puresol.entities.forms.IllegalEntityException;
import com.puresol.entities.forms.TemplateInformation;

abstract public class AbstractQSysBean implements QSysBean {

	private EntityUtility entityUtility = null;

	public AbstractQSysBean() {
	}

	private synchronized void createEntityUtility() {
		if (entityUtility == null) {
			entityUtility = new EntityUtility(getUser());
		} else if (!getUser().equals(entityUtility.getUser())) {
			entityUtility = new EntityUtility(getUser());
		}
	}

	public void setAutomatics(Object entity) throws IllegalEntityException {
		if (entityUtility == null) {
			createEntityUtility();
		} else if (!getUser().equals(entityUtility.getUser())) {
			createEntityUtility();
		}
		entityUtility.setAutomatics(entity);
	}

	public List<?> getAll(Class<?> clazz) {
		return getEntityManager().createNamedQuery(
				clazz.getSimpleName() + ".getAll").getResultList();
	}

	public void insert(Object entity) throws EntityAlreadyExistsException,
			IllegalEntityException {
		TemplateInformation template = TemplateInformation.from(entity);
		Object pk = template.getPrimaryKey();
		if (get(entity.getClass(), pk) != null) {
			throw new EntityAlreadyExistsException(entity.getClass(), pk);
		}
		setAutomatics(entity);
		getEntityManager().persist(entity);
	}

	public Object get(Class<?> clazz, Object id) {
		return getEntityManager().find(clazz, id);
	}

	public void update(Object entity) throws EntityDoesNotExistException,
			IllegalEntityException {
		TemplateInformation template = TemplateInformation.from(entity);
		Object pk = template.getPrimaryKey();
		if (get(entity.getClass(), pk) == null) {
			throw new EntityDoesNotExistException(entity.getClass(), pk);
		}
		setAutomatics(entity);
		getEntityManager().merge(entity);
	}

	public void remove(Object entity) throws EntityDoesNotExistException {
		TemplateInformation template = TemplateInformation.from(entity);
		Class<?> clazz = entity.getClass();
		Object pk = template.getPrimaryKey();
		entity = get(clazz, pk); // replace with managed entity
		if (entity == null) {
			throw new EntityDoesNotExistException(clazz, pk);
		}
		getEntityManager().remove(entity);
	}
}
