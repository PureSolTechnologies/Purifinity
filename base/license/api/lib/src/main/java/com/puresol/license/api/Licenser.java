package com.puresol.license.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * This value object represents a single licenser.
 * 
 * @author Rick-Rainer Ludwig
 */
public class Licenser {

	private final String name;
	private final String email;

	public Licenser(String name, String email) {
		super();
		if ((name == null) || (name.isEmpty())) {
			throw new IllegalArgumentException(
					"The name of the licenser must no be null or empty.");
		}
		this.name = name;
		if ((email == null) || (email.isEmpty())) {
			throw new IllegalArgumentException(
					"The email of the licenser must no be null or empty.");
		}
		EmailValidator emailValidator = EmailValidator.getInstance();
		if (!emailValidator.isValid(email)) {
			throw new IllegalArgumentException("The email '" + email
					+ "' is invalid.");
		}
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Licenser other = (Licenser) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name + " (" + email + ")";
	}

	/**
	 * This method parses the provided string an created a new {@link Licenser}
	 * object.
	 * 
	 * @param licenserString
	 * @return A {@link Licenser} object is returned.
	 */
	public static Licenser fromString(String licenserString) {
		Pattern pattern = Pattern.compile("^(.+)\\s+\\((\\S+)\\)");
		Matcher matcher = pattern.matcher(licenserString);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("String '" + licenserString
					+ "' is not a valid licenser.");
		}
		String name = matcher.group(1);
		String email = matcher.group(2);
		return new Licenser(name, email);
	}
}
