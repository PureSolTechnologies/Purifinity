package com.puresol.license.api;

/**
 * This class contains the information for a licensed class.
 * 
 * @author Rick-Rainer Ludwig
 */
public class LicensedClass {

	private final Class<?> licensedClass;

	public LicensedClass(Class<?> licensedClass) {
		super();
		this.licensedClass = licensedClass;
	}

	public Class<?> getLicensedClass() {
		return licensedClass;
	}
}
