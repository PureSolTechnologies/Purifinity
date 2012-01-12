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

import com.puresol.data.Identifiable;

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

    public static boolean testIdentifiable(Class<? extends Identifiable> clazz) {
	return IdentifiableTester.test(clazz);
    }

    public static boolean testStandards(Class<?> clazz) {
	return StandardsTester.test(clazz);
    }
}
