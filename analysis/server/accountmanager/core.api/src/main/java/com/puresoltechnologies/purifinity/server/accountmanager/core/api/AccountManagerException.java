package com.puresoltechnologies.purifinity.server.accountmanager.core.api;

public class AccountManagerException extends Exception {

    private static final long serialVersionUID = -3437373883401589649L;

    public AccountManagerException(String message, Throwable cause) {
	super(message, cause);
    }

    public AccountManagerException(String message) {
	super(message);
    }

}
