package com.puresol.ua;

import javax.swingx.config.ClassRegistry;

/**
 * This class is a factory class for class registry registered user
 * administrations. The objects used have to be the interface UA implemented.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class UAFactory {

	public static UA create() {
		return (UA) ClassRegistry.create(UA.class);
	}

}
