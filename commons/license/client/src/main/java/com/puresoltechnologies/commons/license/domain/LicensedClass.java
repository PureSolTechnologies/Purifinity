package com.puresoltechnologies.commons.license.domain;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((licensedClass == null) ? 0 : licensedClass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LicensedClass other = (LicensedClass) obj;
		if (licensedClass == null) {
			if (other.licensedClass != null)
				return false;
		} else if (!licensedClass.equals(other.licensedClass))
			return false;
		return true;
	}

}
