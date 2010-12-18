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

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TesterTest extends TestCase {

	
	@Test
	public void testGetterAndSetterTester() {
		Assert.assertTrue(Tester
				.testGetterAndSetter(GetterAndSetter4Testing.class));
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
		Assert.assertTrue(Tester.testClone(object));
	}

}
