package com.puresol.utils;

public class MethodInvokationException extends Exception {

    private static final long serialVersionUID = -6315588482782888073L;

    public MethodInvokationException(Class<?> clazz, String methodName,
	    Class<?> returnType, Class<?>[] parameterClasses) {
	super("Could not invoke method '" + returnType.getSimpleName()
		+ " " + clazz.getName() + "." + methodName + "("
		+ parameterClasses.toString() + ")");
    }

}
