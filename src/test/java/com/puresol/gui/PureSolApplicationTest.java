package com.puresol.gui;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.testing.Tester;

public class PureSolApplicationTest {

	@Test
	public void testStandards() {
		assertTrue(Tester.testStandards(PureSolApplication.class));
	}

}
