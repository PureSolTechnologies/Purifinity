package com.puresol.passwordstore.core.impl.entities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * This method returns a {@link UserEntity} by the given email address which
	 * is has a unique constraint.
	 * 
	 * @param email
	 *            is the email address to select the user account.
	 * @return The {@link UserEntity} is returned for the given account.
	 */
	public UserEntity getUserByEmail(String email) {
		TypedQuery<UserEntity> namedQuery = entityManager.createNamedQuery(
				"SelectUserByEmail", UserEntity.class);
		namedQuery.setParameter("email", email);
		List<UserEntity> resultList = namedQuery.getResultList();
		if (resultList.size() == 0) {
			return null;
		}
		if (resultList.size() > 1) {
			throw new RuntimeException(
					"There should be only one account with a given email address!"
							+ " Here is something wrong in the database setup for the unique constraint for account emails!");
		}
		return resultList.get(0);
	}

}
