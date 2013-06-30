package com.puresol.purifinity.license.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresol.purifinity.license.api.Licenser;

public class LicenserTest {

	@Test(expected = IllegalArgumentException.class)
	public void testInvlidNameNull() {
		new Licenser(null, "contact@puresol-technologies.com");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvlidNameEmpty() {
		new Licenser("", "contact@puresol-technologies.com");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvlidEmailNull() {
		new Licenser("PureSol Techologies", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvlidEmailEmpty() {
		new Licenser("PureSol Techologies", "");
	}

	@Test
	public void testFromString() {
		assertEquals(new Licenser("name", "a@a.de"),
				Licenser.fromString("name (a@a.de)"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringInvalidMissingName() {
		Licenser.fromString(" (a@a.de)");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringInvalidMissingEmail() {
		Licenser.fromString("licenser");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringInvalidEmail() {
		Licenser.fromString("licenser (a.de)");
	}
}
