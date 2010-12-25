package com.puresol.data;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.testing.Tester;

public class PhoneTypeTest {

	@Test
	public void testIdentifiable() {
		assertTrue(Tester.testIdentifiable(PhoneType.class));
	}
}
