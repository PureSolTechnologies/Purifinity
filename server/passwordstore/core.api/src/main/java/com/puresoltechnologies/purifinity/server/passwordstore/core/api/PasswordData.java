package com.puresoltechnologies.purifinity.server.passwordstore.core.api;

/**
 * In lack of tuples we use this class to return the split date from an
 * encrypted password. The password is split into the method id and the actual
 * encrypted password. This class is designed as pure value object.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PasswordData {

	private final int method;

	private final String encryptedPassword;

	/**
	 * This is the initial value constructor.
	 * 
	 * @param method
	 *            is the method id.
	 * @param encryptedPassword
	 */
	public PasswordData(int method, String encryptedPassword) {
		super();
		this.method = method;
		this.encryptedPassword = encryptedPassword;
	}

	/**
	 * This method returns the method id of the encrypter used.
	 * 
	 * @return Returns the id of the encrypter.
	 */
	public int getMethod() {
		return method;
	}

	/**
	 * This method returns the encrypted password.
	 * 
	 * @return Returns the encrypted password.
	 */
	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	@Override
	public String toString() {
		return String.valueOf(method) + ":" + encryptedPassword;
	}

	public static PasswordData fromString(String pwh) {
		int colonIndex = pwh.indexOf(":");
		if (colonIndex < 1) {
			throw new IllegalArgumentException("Invalid password hash '" + pwh
					+ "'!");
		}

		int method = Integer.valueOf(pwh.substring(0, colonIndex));
		String encryptedPassword = pwh.substring(colonIndex + 1);
		return new PasswordData(method, encryptedPassword);
	}
}
