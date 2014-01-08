package com.puresoltechnologies.commons.misc;

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
	 * objects which have final fields and therefore a final hashCode. This
	 * speeds up processing.
	 * 
	 * @param fields
	 *            are a list of field objects to calculate the hash code with.
	 * @return An integer is returned containing the hash result.
	 */
	public static int calculateConstantHashCode(Object... fields) {
		int result = 1;
		for (Object field : fields) {
			result = PRIME * result + ((field == null) ? 0 : field.hashCode());
		}
		return result;
	}

	/**
	 * Private constructor to avoid instantiation.
	 */
	private ObjectUtilities() {
	}
}
