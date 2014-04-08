package com.puresol.commons.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

public class SupportedCountriesTest {

	@Test
	public void testGetCountryCodes() {
		String[] countryCodes = SupportedCountries.getCountryCodes();
		assertNotNull("No country codes are defined!", countryCodes);
		assertTrue("No country codes are defined!", countryCodes.length > 0);

		List<String> isoCountries = Arrays.asList(Locale.getISOCountries());
		for (String countryCode : countryCodes) {
			assertTrue("Illegal country code '" + countryCode
					+ "' was defined!", isoCountries.contains(countryCode));
		}
	}

}
