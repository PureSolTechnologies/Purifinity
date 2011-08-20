package com.puresol.data;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.testing.Tester;

public class PersonNamePartTypeTest {

	@Test
	public void testIdentifiable() {
		assertTrue(Tester.testIdentifiable(PersonNamePartType.class));
	}

	@Test
	public void testStandards() {
		assertTrue(Tester.testStandards(PersonNamePartType.class));
	}
}
