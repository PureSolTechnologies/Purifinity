package com.puresoltechnologies.toolshed.jpcap;

public class JPCapException extends Exception {

    private static final long serialVersionUID = 5890908729897281170L;

    public JPCapException() {
	super();
    }

    public JPCapException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public JPCapException(String message, Throwable cause) {
	super(message, cause);
    }

    public JPCapException(String message) {
	super(message);
    }

    public JPCapException(Throwable cause) {
	super(cause);
    }

}
