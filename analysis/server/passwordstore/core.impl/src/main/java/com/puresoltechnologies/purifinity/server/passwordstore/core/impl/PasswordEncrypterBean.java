package com.puresoltechnologies.purifinity.server.passwordstore.core.impl;

import javax.ejb.Stateless;

import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordData;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordEncryptionException;
import com.puresoltechnologies.purifinity.server.passwordstore.utils.encrypt.Encrypter1;

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
