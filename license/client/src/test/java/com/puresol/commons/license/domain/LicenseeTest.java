package com.puresol.commons.license.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresol.commons.license.domain.Licensee;

public class LicenseeTest {

	@Test(expected = IllegalArgumentException.class)
	public void testInvlidIdNull() {
		new Licensee(null, "licensee");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvlidNameEmpty() {
		new Licensee("", "licensee");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvlidEmailNull() {
		new Licensee("id", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvlidEmailEmpty() {
		new Licensee("id", "");
	}

	@Test
	public void testFromString() {
		assertEquals(new Licensee("id", "licensee"),
				Licensee.fromString("licensee (id)"));
	}

}
