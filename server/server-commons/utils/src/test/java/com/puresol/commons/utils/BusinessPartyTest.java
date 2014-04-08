package com.puresol.commons.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;

public class BusinessPartyTest {

	/**
	 * Check that all names are present and that the correct names are applied
	 * to the enum constants.
	 */
	@Test
	public void testForCompleteNames() {
		for (BusinessParty party : BusinessParty.class.getEnumConstants()) {
			String name = party.getName(Locale.US);
			assertNotNull(name);
			assertFalse(name.isEmpty());
			name = name.toUpperCase();
		}
	}

}
