package com.puresol.license.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	public void testCompareEqualsAndBuildInformationDoNotCare() {
		equals(new Version(0, 0, 0), new Version(0, 0, 0, null, "alpha"));
		equals(new Version(0, 0, 0, null, "alpha"), new Version(0, 0, 0, null,
				"alpha1"));
		equals(new Version(0, 0, 0, null, "alpha.0"), new Version(0, 0, 0,
				null, "alpha.1"));

		equals(new Version(0, 0, 0, "alpha", "alpha"), new Version(0, 0, 0,
				"alpha"));
		equals(new Version(0, 0, 0, "alpha", "alpha1"), new Version(0, 0, 0,
				"alpha", "alpha"));
		equals(new Version(0, 0, 0, "alpha", "alpha.1"), new Version(0, 0, 0,
				"alpha", "alpha.0"));
	}

	@Test
	public void testCompareSmaller() {
		smaller(new Version(0, 0, 0), new Version(0, 0, 1));
		smaller(new Version(0, 0, 0), new Version(0, 1, 0));
		smaller(new Version(0, 0, 0), new Version(1, 0, 0));
		smaller(new Version(0, 0, 0, "alpha"), new Version(0, 0, 0));
		smaller(new Version(0, 0, 0, "alpha"), new Version(0, 0, 0, "alpha1"));
		smaller(new Version(0, 0, 0, "alpha.0"),
				new Version(0, 0, 0, "alpha.1"));
	}

	@Test
	public void testCompareGreater() {
		greater(new Version(0, 0, 1), new Version(0, 0, 0));
		greater(new Version(0, 1, 0), new Version(0, 0, 0));
		greater(new Version(1, 0, 0), new Version(0, 0, 0));
		greater(new Version(0, 0, 0), new Version(0, 0, 0, "alpha"));
		greater(new Version(0, 0, 0, "alpha1"), new Version(0, 0, 0, "alpha"));
		greater(new Version(0, 0, 0, "alpha.1"),
				new Version(0, 0, 0, "alpha.0"));
	}

	@Test
	public void testCompareWithExamplesFromSpecification() {
		Version version1 = Version.fromString("1.0.0-alpha");
		Version version2 = Version.fromString("1.0.0-alpha.1");
		Version version3 = Version.fromString("1.0.0-beta.2");
		Version version4 = Version.fromString("1.0.0-beta.11");
		Version version5 = Version.fromString("1.0.0-rc.1");
		Version version6 = Version.fromString("1.0.0");
		smaller(version1, version2);
		smaller(version2, version3);
		smaller(version3, version4);
		smaller(version4, version5);
		smaller(version5, version6);
	}

	private void equals(Version version1, Version version2) {
		assertTrue(version1.compareTo(version2) == 0);
	}

	private void greater(Version version1, Version version2) {
		assertTrue(version1.compareTo(version2) > 0);
	}

	private void smaller(Version version1, Version version2) {
		assertTrue(version1.compareTo(version2) < 0);
	}

	@Test
	public void testFromString() {
		assertEquals(new Version(1, 2, 3), Version.fromString("1.2.3"));
		assertEquals(new Version(1, 2, 3, "alpha"),
				Version.fromString("1.2.3-alpha"));
		assertEquals(new Version(1, 2, 3, "alpha", "20130519"),
				Version.fromString("1.2.3-alpha+20130519"));
		assertEquals(new Version(1, 2, 3, null, "20130519"),
				Version.fromString("1.2.3+20130519"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringInvalidEmptyPreRelease() {
		Version.fromString("1.2.3-+20130519");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringInvalidEmtpyMetaData() {
		Version.fromString("1.2.3-alpha+");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringInvalidWithTwoNumbersOnly() {
		Version.fromString("1.2");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringInvalidWithForNumbers() {
		Version.fromString("1.2.3.4");
	}

}
