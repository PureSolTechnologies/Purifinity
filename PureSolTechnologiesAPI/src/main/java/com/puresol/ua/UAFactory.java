package com.puresol.ua;

import com.puresol.config.ClassRegistry;

/**
 * This class is a factory class for class registry registered user
 * administrations. The objects used have to be the interface UA implemented.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class UAFactory {

	/**
	 * This method creates a new UA.
	 * 
	 * @param clazz
	 *            is used to determine the correct resource file for
	 *            information.
	 * @return
	 */
	public static UA create(Class<?> clazz) {
		return (UA) ClassRegistry.create(UA.class);
	}

}
