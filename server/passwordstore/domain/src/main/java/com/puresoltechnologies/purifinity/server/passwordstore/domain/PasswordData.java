package com.puresoltechnologies.purifinity.server.passwordstore.domain;

import java.util.Objects;

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
    private final int hash;

    /**
     * This is the initial value constructor.
     * 
     * @param method
     *            is the method id.
     * @param encryptedPassword
     */
    public PasswordData(int method, String encryptedPassword) {
	super();
	if (method <= 0) {
	    throw new IllegalArgumentException(
		    "Method must not be zero or negative.");
	}
	this.method = method;
	if (encryptedPassword == null) {
	    throw new NullPointerException(
		    "The encrypted password must not be null.");
	}
	if (encryptedPassword.isEmpty()) {
	    throw new IllegalArgumentException(
		    "The encrypted password must not be empty.");
	}
	this.encryptedPassword = encryptedPassword;
	hash = Objects.hash(method, encryptedPassword);
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
    public int hashCode() {
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	PasswordData other = (PasswordData) obj;
	if (encryptedPassword == null) {
	    if (other.encryptedPassword != null)
		return false;
	} else if (!encryptedPassword.equals(other.encryptedPassword))
	    return false;
	if (method != other.method)
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return String.valueOf(method) + ":" + encryptedPassword;
    }

    public static PasswordData fromString(String passwordData) {
	int colonIndex = passwordData.indexOf(":");
	if (colonIndex < 0) {
	    throw new IllegalArgumentException(
		    "Invalid format of password data (no colon found).");
	}
	if (colonIndex == 0) {
	    throw new IllegalArgumentException(
		    "Invalid format of password data (no method defined).");
	}
	if (colonIndex == passwordData.length() - 1) {
	    throw new IllegalArgumentException(
		    "Invalid format of password data (no encrypted password defined).");
	}
	String methodString = passwordData.substring(0, colonIndex);
	String encryptedPassword = passwordData.substring(colonIndex + 1);
	if (encryptedPassword.indexOf(":") >= 0) {
	    throw new IllegalArgumentException(
		    "Invalid format of password data (multiple colons found).");
	}
	int method = Integer.valueOf(methodString);
	return new PasswordData(method, encryptedPassword);
    }
}
