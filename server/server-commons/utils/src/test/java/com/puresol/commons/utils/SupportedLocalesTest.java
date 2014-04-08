package com.puresol.commons.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;

public class SupportedLocalesTest {

	@Test
	public void testIsoLanguages() {
		String[] isoLanguages = Locale.getISOLanguages();
		for (String isoLanguage : isoLanguages) {
			System.out.println(isoLanguage);
		}
	}

	@Test
	public void testGetLocales() {
		Locale[] locales = SupportedLocales.getLocales();
		assertNotNull("No supported locales are defined!", locales);
		assertTrue("No supported locales are defined!", locales.length > 0);
	}

	@Test
	public void testGetSuitableLocale() {
		assertEquals(Locale.US,
				SupportedLocales.findSuitableSupportedLocale(Locale.US));
		assertEquals(Locale.US,
				SupportedLocales.findSuitableSupportedLocale(Locale.ENGLISH));

		assertEquals(Locale.GERMANY,
				SupportedLocales.findSuitableSupportedLocale(Locale.GERMANY));
		assertEquals(Locale.GERMANY,
				SupportedLocales.findSuitableSupportedLocale(new Locale("de",
						"AU")));

		assertEquals(Locale.US,
				SupportedLocales.findSuitableSupportedLocale(Locale.ITALY));
	}
}
