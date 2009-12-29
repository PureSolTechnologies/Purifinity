/***************************************************************************
 *
 *   QSysBean.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.appserv;

import java.util.List;

import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

import com.puresol.entities.forms.IllegalEntityException;

/**
 * This interface is base for all QSys beans which should be handled in the same
 * common way with special designed support like automatic content introduction,
 * template generator and so forth...
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface QSysBean {
	public String getUser();

	public SessionContext getSessionContext();

	public EntityManager getEntityManager();

	public void setAutomatics(Object entity) throws IllegalEntityException;

	public List<?> getAll(Class<?> clazz);

	public void insert(Object entity) throws EntityAlreadyExistsException,
			IllegalEntityException;

	public Object get(Class<?> clazz, Object id);

	public void update(Object entity) throws EntityDoesNotExistException,
			IllegalEntityException;

	public void remove(Object entity) throws EntityDoesNotExistException;
}
