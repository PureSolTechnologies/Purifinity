package com.puresoltechnologies.purifinity.server.accountmanager.core.api;

import com.puresoltechnologies.commons.types.EmailAddress;

public class AccountManagerPrincipalImpl implements AccountManagerPrincipal {

    private static final long serialVersionUID = 1723210782820008886L;

    private final EmailAddress name;

    public AccountManagerPrincipalImpl(EmailAddress name) {
	this.name = name;
    }

    @Override
    public String getName() {
	return name.getAddress();
    }

    @Override
    public String getRealName() {
	return "RealName";
    }

}
