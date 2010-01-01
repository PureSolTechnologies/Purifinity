package com.puresol.ua;

import javax.swingx.config.ClassRegistry;

/**
 * This class is a factory class for class registry registered user
 * administrations.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class UAFactory {

	public static UA create() {
		return (UA) ClassRegistry.create(UA.class);
	}

}
