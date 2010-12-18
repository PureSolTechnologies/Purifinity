package com.puresol.combatcoding;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * This testcase checks the Combat Coding class CC for correction functionality.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CCTest extends TestCase {

	private static class PrimitiveTest implements Cloneable {
		public int a;
		public int b;
	}

	@Test
	public void testEquals() {
		PrimitiveTest test = new PrimitiveTest();
		test.a = 1;
		test.b = 2;
		assertTrue(CC.equals(test, test));
		assertFalse(CC.equals(null, test));
		assertFalse(CC.equals(test, null));

		PrimitiveTest test2 = new PrimitiveTest();
		test2.a = 1;
		test2.b = 2;
		assertTrue(CC.equals(test, test2));
		test2.a = 2;
		test2.b = 3;
		assertFalse(CC.equals(test, test2));
		test2.a = 2;
		test2.b = 2;
		assertFalse(CC.equals(test, test2));
	}

	@Test
	public void testHashCode() {
		PrimitiveTest test = new PrimitiveTest();
		test.a = 1;
		test.b = 2;
		int hashCode = CC.hashCode(test);
		assertEquals(1, test.a);
		assertEquals(2, test.b);
		assertTrue(hashCode != 0);
	}

	@Test
	public void testClone() {
		try {
			PrimitiveTest test = new PrimitiveTest();
			test.a = 1;
			test.b = 2;
			PrimitiveTest test2 = CC.clone(test);
			assertNotSame(test, test2);
			assertEquals(CC.hashCode(test), CC.hashCode(test2));
			assertTrue(CC.equals(test, test2));
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			fail("No Exception was expected!");
		}
	}

}
