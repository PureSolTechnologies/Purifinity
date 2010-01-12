package com.puresol.parser;

public class PartDoesNotMatchException extends Exception {

    private static final long serialVersionUID = 2164154548154513554L;

    public PartDoesNotMatchException(Part part) {
	super("Part '" + part.getClass().getName() + "' does not match!");
    }
}
