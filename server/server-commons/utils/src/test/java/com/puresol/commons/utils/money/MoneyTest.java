package com.puresol.commons.utils.money;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.Test;

public class MoneyTest {

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalCurrencyCode() {
		new Money("NOT", 12, 34);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalLocale() {
		new Money(new Locale("xx", "XX"), 12, 34);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullCurrency() {
		new Money((Currency) null, 12, 34);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullLocale() {
		new Money((Locale) null, 12, 34);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullCurrencyCode() {
		new Money((String) null, 12, 34);
	}

	@Test
	public void testString() {
		Money money = new Money(Currency.getInstance(Locale.GERMANY), 12, 34);
		assertEquals("EUR 12.34", money.toString());
		assertEquals("EUR 12,34", money.toString(Locale.GERMANY));

		money = new Money("USD", 12, 34);
		assertEquals("USD 12.34", money.toString());
		assertEquals("USD 12,34", money.toString(Locale.GERMANY));

		money = new Money("JPY", 12, 34);
		assertEquals("JPY 46", money.toString());
		assertEquals("JPY 46", money.toString(Locale.GERMANY));
	}

	@Test
	public void testAdd() {
		Money m1 = new Money(Currency.getInstance("EUR"), 12345);
		Money m2 = new Money(Currency.getInstance("EUR"), 23456);

		Money added = m1.add(m2);
		assertEquals(BigDecimal.valueOf(358.01), added.getAmount());
	}

	@Test
	public void testSubtract() {
		Money m1 = new Money(Currency.getInstance("EUR"), 12345);
		Money m2 = new Money(Currency.getInstance("EUR"), 23456);

		Money subtracted = m1.subtract(m2);
		assertEquals(BigDecimal.valueOf(-111.11), subtracted.getAmount());
	}

	@Test
	public void testMultiply() {
		Money m1 = new Money(Currency.getInstance("EUR"), 12345);

		Money multiplied = m1.multiply(0.19);
		assertEquals(BigDecimal.valueOf(23.46), multiplied.getAmount());
	}

}
