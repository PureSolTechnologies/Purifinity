package com.puresoltechnologies.purifinity.server.accountmanager.rest.impl;

import java.util.Set;

import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManager;
import com.puresoltechnologies.purifinity.server.accountmanager.domain.Role;
import com.puresoltechnologies.purifinity.server.accountmanager.domain.User;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.AccountManagerRestInterface;

public class AccountManagerRestService implements AccountManagerRestInterface {

    @Inject
    private AccountManager accountManager;

    @Override
    public Set<User> getUsers() {
	return accountManager.getUsers();
    }

    @Override
    public Set<Role> getRoles() {
	return accountManager.getRoles();
    }

}
