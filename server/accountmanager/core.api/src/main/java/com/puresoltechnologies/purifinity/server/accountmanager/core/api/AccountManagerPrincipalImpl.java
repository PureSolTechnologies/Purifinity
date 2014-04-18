package com.puresoltechnologies.purifinity.server.accountmanager.core.api;

public class AccountManagerPrincipalImpl implements AccountManagerPrincipal {

	private static final long serialVersionUID = 1723210782820008886L;

	private final String name;

	public AccountManagerPrincipalImpl(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getRealName() {
		return "RealName";
	}

}
