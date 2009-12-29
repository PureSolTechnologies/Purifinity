/***************************************************************************
 *
 *   Tester.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.testing;

import com.puresol.entities.EntityTester;

/**
 * Tester is a class with public static methods to get a clean and easy access
 * to all automatic testers.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Tester {

	public static boolean testGetterAndSetter(Class<?> clazz) {
		return GetterAndSetterTester.test(clazz);
	}

	public static boolean testClone(Object object) {
		return CloneTester.test(object);
	}

	public static boolean testEntity(Class<?> clazz) {
		return EntityTester.test(clazz);
	}
}
