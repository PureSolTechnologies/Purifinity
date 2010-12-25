package com.puresol.exceptions;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.testing.Tester;

public class StrangeSituationExceptionTest {

	@Test
	public void testStandards() {
		assertTrue(Tester.testStandards(StrangeSituationException.class));
	}
}
