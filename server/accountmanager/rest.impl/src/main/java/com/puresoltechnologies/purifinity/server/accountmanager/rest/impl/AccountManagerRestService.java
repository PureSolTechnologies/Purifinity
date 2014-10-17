package com.puresoltechnologies.purifinity.server.accountmanager.rest.impl;

import java.util.Set;

import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManager;
import com.puresoltechnologies.purifinity.server.accountmanager.domain.Role;
import com.puresoltechnologies.purifinity.server.accountmanager.domain.User;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.AccountManagerRestInterface;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;

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

    @Override
    public void setUser(String email, User user) {
	accountManager.setUser(email, user);
    }

    @Override
    public User getUser(String email) {
	return accountManager.getUser(email);
    }

    @Override
    public String createAccount(String email, String password)
	    throws AccountCreationException {
	return accountManager.createAccount(email, password);
    }

    @Override
    public String activateAccount(String email, String activationKey)
	    throws AccountActivationException {
	return accountManager.activateAccount(email, activationKey);
    }

    @Override
    public boolean changePassword(String email, String oldPassword,
	    String newPassword) throws PasswordChangeException {
	return accountManager.changePassword(email, oldPassword, newPassword);
    }

    @Override
    public String resetPassword(String email) throws PasswordResetException {
	return accountManager.resetPassword(email);
    }

}
