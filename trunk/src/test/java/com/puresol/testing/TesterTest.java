/***************************************************************************
 *
 *   TesterTest.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.testing;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesterTest {

	@Test
	public void testGetterAndSetterTester() {
		assertTrue(Tester.testGetterAndSetter(GetterAndSetter4Testing.class));
	}

	@Test
	public void testCloneTester() {
		Clone4Testing object = new Clone4Testing();
		object.setBooleanVar(true);
		object.setBooleanVar2(new Boolean(false));
		object.setDoubleVar(3.1415926);
		object.setIntVar(42);
		object.setShortVar((short) 21);
		object.setStringVar("String");
		assertTrue(Tester.testClone(object));
	}

}
