package com.puresol.appserv;

import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

public class EntityManagerExtension extends AbstractQSysBean {

	private SessionContext sessionContext = null;
	private EntityManager entityManager = null;
	private String user = "";

	public EntityManagerExtension(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.user = getClass().getName();
	}

	public SessionContext getSessionContext() {
		return sessionContext;
	}

	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
