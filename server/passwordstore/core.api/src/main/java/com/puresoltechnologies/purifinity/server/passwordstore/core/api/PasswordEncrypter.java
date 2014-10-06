package com.puresoltechnologies.purifinity.server.passwordstore.core.api;

import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordData;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordEncryptionException;

/**
 * This is the central interface for password encrypters. This interface only
 * provides the base functionality: encryption and check for password equality.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface PasswordEncrypter {

	/**
	 * This method encrypts the provided password.
	 * 
	 * @param password
	 *            is the clear text password to be encrypted.
	 * @return A {@link String} containing the encrypted password.
	 * @throws PasswordEncryptionException
	 *             is thrown in exceptional situations.
	 */
	public PasswordData encryptPassword(String password)
			throws PasswordEncryptionException;

	/**
	 * This method performs the password check.
	 * 
	 * @param password
	 *            is the clear text password which is to be checked against the
	 *            already encrypted password.
	 * @param expectedEncryptedPassword
	 *            is the encrypted password which is used as reference for the
	 *            comparison.
	 * @return True is returned if the password matches the encrypted password.
	 *         False is returned otherwise.
	 * @throws PasswordEncryptionException
	 *             is thrown in exceptional situations.
	 */
	public boolean checkPassword(String password,
			PasswordData expectedEncryptedPassword)
			throws PasswordEncryptionException;

}
