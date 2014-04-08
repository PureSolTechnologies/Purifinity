package com.puresol.commons.utils.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * This class represents money. Money consists of an amount of it and a
 * currency.
 * 
 * The class was basically inspired by Martin Fowler's Money implementation
 * found in "Patterns of Enterprise Application Architecture".
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class Money implements Comparable<Money> {

	private static final int[] cents = new int[] { 1, 10, 100, 1000, 10000 };

	private final Currency currency;
	private final long amount;

	public Money(Currency currency, long amountInCents) {
		if (currency == null) {
			throw new IllegalArgumentException("The currency must no be null!");
		}
		this.currency = currency;
		this.amount = amountInCents;
	}

	public Money(String currencyCode, long amountInCents) {
		if (currencyCode == null) {
			throw new IllegalArgumentException(
					"The currency code must no be null!");
		}
		this.currency = Currency.getInstance(currencyCode);
		this.amount = amountInCents;
	}

	public Money(Locale locale, long amountInCents) {
		if (locale == null) {
			throw new IllegalArgumentException("The locale must no be null!");
		}
		this.currency = Currency.getInstance(locale);
		this.amount = amountInCents;
	}

	public Money(Currency currency, long major, int minor) {
		if (currency == null) {
			throw new IllegalArgumentException("The currency must no be null!");
		}
		this.currency = currency;
		this.amount = major * getCentFactor() + minor;
	}

	public Money(String currencyCode, long major, int minor) {
		if (currencyCode == null) {
			throw new IllegalArgumentException(
					"The currency code must no be null!");
		}
		this.currency = Currency.getInstance(currencyCode);
		this.amount = major * getCentFactor() + minor;
	}

	public Money(Locale locale, long major, int minor) {
		if (locale == null) {
			throw new IllegalArgumentException("The locale must no be null!");
		}
		this.currency = Currency.getInstance(locale);
		this.amount = major * getCentFactor() + minor;
	}

	private int getCentFactor() {
		return cents[currency.getDefaultFractionDigits()];
	}

	public Currency getCurrency() {
		return currency;
	}

	public BigDecimal getAmount() {
		return BigDecimal.valueOf(amount, currency.getDefaultFractionDigits());
	}

	@Override
	public String toString() {
		return toString(Locale.US);
	}

	public String toString(Locale locale) {
		DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(
				locale);
		String format = "{0} {1,number}";
		if (currency.getDefaultFractionDigits() > 0) {
			format += decimalFormatSymbols.getDecimalSeparator() + "{2,number,";
			for (int digits = 0; digits < currency.getDefaultFractionDigits(); digits++) {
				format += "#";
			}
			format += "}";
		}
		return MessageFormat.format(format, currency.getCurrencyCode(), amount
				/ getCentFactor(), amount % getCentFactor());
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof Money) && equals((Money) other);
	}

	public boolean equals(Money other) {
		return currency.equals(other.currency) && (amount == other.amount);
	}

	@Override
	public int hashCode() {
		return (int) (amount ^ (amount >>> 32));
	}

	@Override
	public int compareTo(Money other) {
		if (isSameCurrency(other)) {
			if (amount < other.amount) {
				return -1;
			} else if (amount > other.amount) {
				return 1;
			} else {
				return 0;
			}
		} else {
			return compareTo(other.convertTo(currency));
		}
	}

	public boolean isGreaterThan(Money other) {
		return (compareTo(other) > 0);
	}

	public boolean isLessThan(Money other) {
		return (compareTo(other) < 0);
	}

	private boolean isSameCurrency(Money other) {
		if (currency.equals(other.currency)) {
			return true;
		}
		return false;
	}

	public Money add(Money other) {
		if (isSameCurrency(other)) {
			return new Money(currency, amount + other.amount);
		} else {
			return add(other.convertTo(currency));
		}
	}

	public Money subtract(Money other) {
		if (isSameCurrency(other)) {
			return new Money(currency, amount - other.amount);
		} else {
			return subtract(other.convertTo(currency));
		}
	}

	public Money convertTo(Currency newCurrency) {
		throw new IllegalArgumentException(
				"Converting from one currency to another is not implemented, yet!");
	}

	public Money multiply(double factor) {
		return multiply(new BigDecimal(factor));
	}

	public Money multiply(BigDecimal factor) {
		return multiply(factor, RoundingMode.HALF_EVEN);
	}

	private Money multiply(BigDecimal factor, RoundingMode roundingMode) {
		BigDecimal newAccurateAmount = BigDecimal.valueOf(amount)
				.multiply(factor).setScale(0, RoundingMode.HALF_EVEN);
		long newAmount = newAccurateAmount.longValue();
		return new Money(currency, newAmount);
	}
}
