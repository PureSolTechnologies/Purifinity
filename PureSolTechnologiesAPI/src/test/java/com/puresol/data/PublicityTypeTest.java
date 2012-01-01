package com.puresol.data;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.testing.Tester;

public class PublicityTypeTest {

	@Test
	public void testIdentifiable() {
		assertTrue(Tester.testIdentifiable(PublicityType.class));
	}

}
