package com.puresoltechnologies.purifinity.server.wildfly.utils;

public class JndiUtils {

	public static String createGlobalAddress(String earName, String ejbName,
			Class<?> interfaceClass, Class<?> implementationClass) {
		if (!interfaceClass.isInterface()) {
			throw new IllegalArgumentException("Class '"
					+ interfaceClass.getName()
					+ "' is not an interface as expected.");
		}
		if (implementationClass.isInterface()) {
			throw new IllegalArgumentException("Class '"
					+ implementationClass.getName()
					+ "' is not a class as expected.");
		}
		if (!interfaceClass.isAssignableFrom(implementationClass)) {
			throw new IllegalArgumentException("Class '"
					+ implementationClass.getName()
					+ "' is not implementing interface '"
					+ interfaceClass.getName() + "' as expected.");
		}
		return "java:global/" + earName + "/" + ejbName + "/"
				+ implementationClass.getSimpleName() + "!"
				+ interfaceClass.getName();
	}

}
