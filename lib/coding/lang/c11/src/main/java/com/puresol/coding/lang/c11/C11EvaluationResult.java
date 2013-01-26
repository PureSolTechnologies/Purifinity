package com.puresol.coding.lang.c11;

public class C11EvaluationResult {

    private final Object result;

    public C11EvaluationResult(Object result) {
	this.result = result;
    }

    public boolean isBooleanResult() {
	return Boolean.class.isAssignableFrom(result.getClass());
    }

    public boolean isIntegerResult() {
	return Integer.class.isAssignableFrom(result.getClass());
    }

    public boolean getBooleanValue() throws EvaluationException {
	if (isBooleanResult()) {
	    return (Boolean) result;
	}
	throw new EvaluationException("Result is not boolean!");
    }

}
