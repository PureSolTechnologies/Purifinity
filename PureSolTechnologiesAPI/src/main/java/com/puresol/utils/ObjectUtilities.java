package com.puresol.utils;

/**
 * This class contains helper functions for object creation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ObjectUtilities {

    /**
     * This method can be used to caluclate a constant hash code for value
     * objects.
     * 
     * @param fields
     * @return
     */
    public static int calculateConstantHashCode(Object... fields) {
	final int prime = 31;
	int result = 1;
	for (Object field : fields) {
	    result = prime * result + ((field == null) ? 0 : field.hashCode());
	}
	return result;
    }

}
