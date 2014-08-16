package com.puresoltechnologies.commons.math.money;

import java.io.Serializable;
import java.util.Arrays;

import com.puresoltechnologies.commons.math.MathUtils;

/**
 * This class is inspired by the Money implementation in Fowler's book
 * "Enterprise Application Patterns".
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Money implements Comparable<Money>, Serializable {

	private static final long serialVersionUID = -6697323264404131582L;

	/**
	 * This field keeps the currency symbol of the currency.
	 */
	private final String currency;

	/**
	 * This field contains the amount of the currency. This field contains the
	 * amount in the smallest unit for the specified currency! For Dollar it
	 * would be Cent for instance.
	 */
	private final long amount;

	/**
	 * This field contains the fraction of the currency's smallest unit to the
	 * major unit.
	 */
	private final int fraction;

	public Money(String currency, int fraction, long amount) {
		if (currency == null) {
			throw new IllegalArgumentException("Currency must not be null");
		}
		this.currency = currency;
		this.fraction = fraction;
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public long getAmount() {
		return amount;
	}

	public int getFraction() {
		return fraction;
	}

	@Override
	public String toString() {
		long major = amount / fraction;
		long minor = amount % fraction;
		StringBuilder builder = new StringBuilder();
		builder.append(major);
		if (minor != 0) {
			builder.append(".");
			builder.append(minor);
		}
		builder.append(currency);
		return builder.toString();
	}

	/**
	 * This method calculates shares of this money object which are weigthed by
	 * given ratios.
	 * 
	 * <b>Attention:</b> It is not guaranteed that all shares have exactly the
	 * ratios to each other as specified due to the side condition that the sum
	 * of all shares need to be <b>exactly</b> the source amount of money. The
	 * error of the share difference is maximal the amount of the smallest
	 * possible money amount.
	 * 
	 * @param ratios
	 *            are the ration to be taken into acount for share calculation.
	 * @return An array of {@link Money} is returned containing the shares.
	 */
	public Money[] share(double... ratios) {
		if (ratios.length == 0) {
			throw new IllegalArgumentException(
					"At least one ration value needs to be provided.");
		}
		long[] amountAllocation = MathUtils.allocate(amount, ratios);
		Money allocation[] = new Money[ratios.length];
		for (int i = 0; i < ratios.length; i++) {
			allocation[i] = new Money(currency, fraction, amountAllocation[i]);
		}
		return allocation;
	}

	/**
	 * The method calculates equal shares. Only the number of shares need to be
	 * specified.
	 * 
	 * <b>Attention:</b> It is not guaranteed that all shares have an equal
	 * amount due to the side condition that the sum of all shares need to be
	 * <b>exactly</b> the source amount of money. The error of the share
	 * difference is maximal the amount of the smallest possible money amount.
	 * 
	 * @param numberOfShares
	 *            is the number of shares the current amount of money is split
	 *            into.
	 * @return An array of {@link Money} is returned containing the shares.
	 */
	public Money[] share(int numberOfShares) {
		if (numberOfShares <= 0) {
			throw new IllegalArgumentException(
					"Number of shares has to be greater than zero.");
		}
		int ratios[] = new int[numberOfShares];
		Arrays.fill(ratios, 1);
		long[] amountAllocation = MathUtils.allocate(amount, ratios);
		Money allocation[] = new Money[ratios.length];
		for (int i = 0; i < ratios.length; i++) {
			allocation[i] = new Money(currency, fraction, amountAllocation[i]);
		}
		return allocation;
	}

	@Override
	public int compareTo(Money o) {
		if (!currency.equals(o.currency)) {
			throw new IllegalArgumentException(
					"Comparing two money values is only supported with same currency, got '"
							+ currency + "' and '" + o.currency + "'.");
		}
		if (fraction != o.fraction) {
			throw new IllegalArgumentException(
					"Comparing two money values is only supported with same fraction, got '"
							+ fraction + "' and '" + o.fraction + "'.");
		}
		return Long.valueOf(amount).compareTo(o.amount);
	}
}
