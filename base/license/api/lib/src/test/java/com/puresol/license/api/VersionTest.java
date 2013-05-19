package com.puresol.license.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VersionTest {

	@Test
	public void testMinimumVersion() {
		Version version = new Version(1, 2, 3);
		assertEquals("1.2.3", version.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeMajor() {
		new Version(-1, 2, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeMinor() {
		new Version(1, -2, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativePatch() {
		new Version(1, 2, -3);
	}

	@Test
	public void testMinimumVersionPlusPreRelease() {
		Version version = new Version(1, 2, 3, "alpha");
		assertEquals("1.2.3-alpha", version.toString());
	}

	@Test
	public void testMinimumVersionPlusPreReleasePlusMetaData() {
		Version version = new Version(1, 2, 3, "alpha", "20130519");
		assertEquals("1.2.3-alpha+20130519", version.toString());
	}

	@Test
	public void testMinimumVersionPlusPlusMetaData() {
		Version version = new Version(1, 2, 3, null, "20130519");
		assertEquals("1.2.3+20130519", version.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPreRelease() {
		new Version(1, 2, 3, "alpha-1", "20130519");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidMetaData() {
		new Version(1, 2, 3, "alpha", "20130519-150000");
	}

	@Test
	public void testCompareEquals() {
		equals(new Version(0, 0, 0), new Version(0, 0, 0));
		equals(new Version(0, 0, 0, "alpha"), new Version(0, 0, 0, "alpha"));
		equals(new Version(0, 0, 0, "alpha", "20130519"), new Version(0, 0, 0,
				"alpha", "20130519"));
	}

	@Test
	public void testCompareSmaller() {
		smaller(new Version(0, 0, 0), new Version(0, 0, 1));
		smaller(new Version(0, 0, 0), new Version(0, 1, 0));
		smaller(new Version(0, 0, 0), new Version(1, 0, 0));
		smaller(new Version(0, 0, 0), new Version(0, 0, 0, "alpha"));
		smaller(new Version(0, 0, 0, "alpha"), new Version(0, 0, 0, "alpha1"));
		smaller(new Version(0, 0, 0, "alpha.0"),
				new Version(0, 0, 0, "alpha.1"));
		smaller(new Version(0, 0, 0), new Version(0, 0, 0, null, "alpha"));
		smaller(new Version(0, 0, 0, null, "alpha"), new Version(0, 0, 0, null,
				"alpha1"));
		smaller(new Version(0, 0, 0, null, "alpha.0"), new Version(0, 0, 0,
				null, "alpha.1"));
	}

	private void equals(Version version1, Version version2) {
		greater(new Version(0, 0, 1), new Version(0, 0, 0));
		greater(new Version(0, 1, 0), new Version(0, 0, 0));
		greater(new Version(1, 0, 0), new Version(0, 0, 0));
		greater(new Version(0, 0, 0, "alpha"), new Version(0, 0, 0));
		greater(new Version(0, 0, 0, "alpha1"), new Version(0, 0, 0, "alpha"));
		greater(new Version(0, 0, 0, "alpha.1"),
				new Version(0, 0, 0, "alpha.0"));
		greater(new Version(0, 0, 0, null, "alpha"), new Version(0, 0, 0));
		greater(new Version(0, 0, 0, null, "alpha1"), new Version(0, 0, 0,
				null, "alpha"));
		greater(new Version(0, 0, 0, null, "alpha.1"), new Version(0, 0, 0,
				null, "alpha.0"));
	}

	private void greater(Version version1, Version version2) {
		compare(version1, version2, 1);
	}

	private void smaller(Version version1, Version version2) {
		compare(version1, version2, -1);
	}

	private void compare(Version version1, Version version2, int expected) {
		assertEquals(expected, version1.compareTo(version2));
	}
}
