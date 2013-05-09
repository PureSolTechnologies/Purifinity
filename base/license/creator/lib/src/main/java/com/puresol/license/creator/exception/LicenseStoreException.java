package com.puresol.license.creator.exception;

import com.puresol.license.creator.store.LicenseStore;

/**
 * This exception is thrown from {@link LicenseStore} in cases of issues.
 * 
 * @author Rick-Rainer Ludwig
 */
public class LicenseStoreException extends Exception {

    private static final long serialVersionUID = -7154046378687136742L;

    public LicenseStoreException(String message, Throwable cause) {
	super(message, cause);
    }

    public LicenseStoreException(String message) {
	super(message);
    }

}
