package com.puresol.passwordstore.core.impl.entities;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class ActivationKeyDAO {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * This method returns the {@link ActivationKeyEntity} for a specified
	 * userId.
	 * 
	 * @param userId
	 *            is the user id to be used search for the activation key.
	 * @return The {@link ActivationKeyEntity} is returned for the given user
	 *         id.
	 */
	public ActivationKeyEntity getActivationKeyByUserId(long userId) {
		TypedQuery<ActivationKeyEntity> namedQuery = entityManager
				.createNamedQuery("GetActivationKey", ActivationKeyEntity.class);
		namedQuery.setParameter("userId", userId);
		ActivationKeyEntity activationKeyEntity = namedQuery.getSingleResult();
		return activationKeyEntity;
	}

}
