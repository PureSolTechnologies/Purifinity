package com.puresol.commons.license.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This value object represents a single licensee.
 * 
 * @author Rick-Rainer Ludwig
 */
public class Licensee {

	/*
	 * TODO add a list of people of the customer which should be notified when
	 * the license expires!
	 */

	private final String customerId;
	private final String name;

	public Licensee(String customerId, String name) {
		super();
		if ((customerId == null) || (customerId.isEmpty())) {
			throw new IllegalArgumentException(
					"Customer id must not be null or empty.");
		}
		this.customerId = customerId;
		if ((name == null) || (name.isEmpty())) {
			throw new IllegalArgumentException(
					"Customer name must not be null or empty.");
		}
		this.name = name;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name + " (" + customerId + ")";
	}

	public static Licensee fromString(String licenseeString) {
		Pattern pattern = Pattern.compile("^(.+)\\s+\\((\\S+)\\)");
		Matcher matcher = pattern.matcher(licenseeString);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("String '" + licenseeString
					+ "' is not a valid licensee.");
		}
		String name = matcher.group(1);
		String customerId = matcher.group(2);
		return new Licensee(customerId, name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Licensee other = (Licensee) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
