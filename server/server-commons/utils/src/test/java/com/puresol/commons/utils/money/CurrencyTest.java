package com.puresol.commons.utils.money;

import java.util.Currency;
import java.util.Locale;

import org.junit.Test;

public class CurrencyTest {

	@Test
	public void testCurrency() {
		for (Locale locale : Locale.getAvailableLocales()) {
			if (locale.getCountry().isEmpty()) {
				continue;
			}
			Currency currency = Currency.getInstance(locale);
			System.out.println(locale.toString() + " : "
					+ currency.getCurrencyCode() + " / "
					+ currency.getSymbol(locale) + " ("
					+ currency.getDefaultFractionDigits() + ")");
		}
	}

}
