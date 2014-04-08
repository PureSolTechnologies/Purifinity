package com.puresol.commons.utils;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;

public class ExtendedExceptionTest {

	/**
	 * This test checks the condition for the default locale. The default locale
	 * needs to be {@link Locale#US} with the string representation en_US.
	 */
	@Test
	public void testEnvironmentPreConditions() {
		assertEquals("en_US", Locale.US.toString());
	}

}
