package com.puresoltechnologies.purifinity.server.accountmanager.rest.impl;

import java.util.Set;

import javax.inject.Inject;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManager;
import com.puresoltechnologies.purifinity.server.accountmanager.domain.Role;
import com.puresoltechnologies.purifinity.server.accountmanager.domain.User;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.AccountManagerRestInterface;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
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
    public void setUser(EmailAddress email, User user) {
	accountManager.setUser(email, user);
    }

    @Override
    public User getUser(EmailAddress email) {
	return accountManager.getUser(email);
    }

    @Override
    public String createAccount(String email, String password)
	    throws PasswordCreationException {
	return accountManager.createPassword(new EmailAddress(email),
		new Password(password));
    }

    @Override
    public EmailAddress activateAccount(String email, String activationKey)
	    throws PasswordActivationException {
	return accountManager.activatePassword(new EmailAddress(email),
		activationKey);
    }

    @Override
    public boolean changePassword(String email, String oldPassword,
	    String newPassword) throws PasswordChangeException {
	return accountManager.changePassword(new EmailAddress(email),
		new Password(oldPassword), new Password(newPassword));
    }

    @Override
    public Password resetPassword(String email) throws PasswordResetException {
	return accountManager.resetPassword(new EmailAddress(email));
    }

}
