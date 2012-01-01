package com.puresol.data;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.testing.Tester;

public class CountryTest {

	@Test
	public void testConstructor() {
		try {
			Country country = new Country("de");
			assertEquals("DE", country.getISOCountry());
			Country country2 = new Country(country);
			assertEquals("DE", country2.getISOCountry());
		} catch (IllegaleCountryCodeException e) {
			e.printStackTrace();
			fail("No exception was expected here!");
		}
	}

	@Test
	public void testClone() {
		try {
			Country country = new Country("de");
			Tester.testClone(country);
		} catch (IllegaleCountryCodeException e) {
			e.printStackTrace();
			fail("No exception was expected here!");
		}
	}

	@Test
	public void testException() {
		try {
			new Country("IV");
		} catch (IllegaleCountryCodeException e) {
			// nothing to catch, exception was expected...
		}
		try {
			new Country((Country) null);
		} catch (IllegaleCountryCodeException e) {
			// nothing to catch, exception was expected...
		}
	}
}
