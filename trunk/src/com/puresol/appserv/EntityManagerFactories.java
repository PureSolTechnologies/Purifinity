package com.puresol.appserv;

import java.util.HashMap;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class EntityManagerFactories {

	static private HashMap<String, EntityManagerFactory> entityManagerFactories = new HashMap<String, EntityManagerFactory>();

	static public EntityManagerFactory create(Object entity) {
		return create(entity.getClass());
	}

	static public EntityManagerFactory create(Class<?> type) {
		if (type.getAnnotation(Entity.class) == null) {
			throw new IllegalArgumentException("Class '" + type.getName()
					+ "' is not a valid entity class!");
		}
		PersistenceContext persistenceContext = type
				.getAnnotation(PersistenceContext.class);
		if (persistenceContext == null) {
			throw new IllegalArgumentException("Class '" + type.getName()
					+ "' has not defined any PersistenceContext!");
		}
		return create(persistenceContext.unitName());
	}

	static public EntityManagerFactory create(String persistenceContext) {
		if (!entityManagerFactories.containsKey(persistenceContext)) {
			createEntityManager(persistenceContext);
		}
		return entityManagerFactories.get(persistenceContext);
	}

	static private synchronized void createEntityManager(
			String persistenceContext) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(persistenceContext);
		entityManagerFactories.put(persistenceContext, factory);
	}

	public static void shutdown() {
		Set<String> keys = entityManagerFactories.keySet();
		for (String key : keys) {
			EntityManagerFactory manager = create(key);
			entityManagerFactories.remove(key);
			manager.close();
		}
	}
}
