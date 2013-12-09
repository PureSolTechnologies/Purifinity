package com.puresoltechnologies.commons.utils;

/**
 * This class contains helper functions for object creation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ObjectUtilities {

	private static final int PRIME = 31;

	/**
	 * This method can be used to calculate a constant hash code for value
	 * objects.
	 * 
	 * @param fields
	 * @return
	 */
	public static int calculateConstantHashCode(Object... fields) {
		int result = 1;
		for (Object field : fields) {
			result = PRIME * result + ((field == null) ? 0 : field.hashCode());
		}
		return result;
	}

}
