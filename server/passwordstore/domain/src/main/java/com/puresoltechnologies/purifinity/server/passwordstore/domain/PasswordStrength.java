package com.puresoltechnologies.purifinity.server.passwordstore.domain;

public enum PasswordStrength {

	VERY_WEAK("very weak"), WEAK("weak"), MEDIOCRE("mediocre"), STRONG("strong"), VERY_STRONG(
			"very strong");

	private final String string;

	private PasswordStrength(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return string;
	}

}
