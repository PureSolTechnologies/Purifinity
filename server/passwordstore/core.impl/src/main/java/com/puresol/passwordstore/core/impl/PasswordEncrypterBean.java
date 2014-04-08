package com.puresol.passwordstore.core.impl;

import javax.ejb.Stateless;

import com.puresol.passwordstore.core.api.PasswordData;
import com.puresol.passwordstore.core.api.PasswordEncrypter;
import com.puresol.passwordstore.domain.PasswordEncryptionException;

/**
 * This is the central place for oneway password encryption. The encrypter is
 * designed to be extensible in future.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
@Stateless
public class PasswordEncrypterBean implements PasswordEncrypter {

	private static final int DEFAULT_METHOD = 1;

	@Override
	public PasswordData encryptPassword(String password)
			throws PasswordEncryptionException {
		return encrypt(password);
	}

	@Override
	public boolean checkPassword(String password, PasswordData expectedPassword)
			throws PasswordEncryptionException {
		return check(password, expectedPassword);
	}

	private static PasswordData encrypt(String password)
			throws PasswordEncryptionException {
		switch (DEFAULT_METHOD) {
		case 1:
			return new PasswordData(1, Encrypter1.encrypt(password));
		default:
			throw new PasswordEncryptionException("Encryption method "
					+ DEFAULT_METHOD + " is not supported!");
		}
	}

	private static boolean check(String password, PasswordData expectedPassword)
			throws PasswordEncryptionException {
		int method = expectedPassword.getMethod();
		switch (method) {
		case 1:
			return Encrypter1.check(password,
					expectedPassword.getEncryptedPassword());
		default:
			throw new PasswordEncryptionException("Encryption method " + method
					+ " is not supported!");
		}
	}

}
