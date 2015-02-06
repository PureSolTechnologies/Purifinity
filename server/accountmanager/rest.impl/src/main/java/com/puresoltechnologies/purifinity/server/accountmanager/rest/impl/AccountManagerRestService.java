package com.puresoltechnologies.purifinity.server.accountmanager.rest.impl;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManager;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.AccountManagerRestInterface;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.Role;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.User;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;

public class AccountManagerRestService implements AccountManagerRestInterface {

    @Inject
    private AccountManager accountManager;

    @Override
    public Set<User> getUsers() {
	Set<User> users = new HashSet<>();
	for (com.puresoltechnologies.purifinity.server.accountmanager.core.api.User user : accountManager
		.getUsers()) {
	    com.puresoltechnologies.purifinity.server.accountmanager.core.api.Role role = user
		    .getRole();
	    users.add(new User(user.getEmail().getAddress(), user.getName(),
		    new Role(role.getId(), role.getName())));
	}
	return users;
    }

    @Override
    public Set<Role> getRoles() {
	Set<Role> roles = new HashSet<>();
	for (com.puresoltechnologies.purifinity.server.accountmanager.core.api.Role role : accountManager
		.getRoles()) {
	    roles.add(new Role(role.getId(), role.getName()));
	}
	return roles;
    }

    @Override
    public void setUser(String email, User user) {
	com.puresoltechnologies.purifinity.server.accountmanager.core.api.Role role = new com.puresoltechnologies.purifinity.server.accountmanager.core.api.Role(
		user.getRole().getId(), user.getRole().getName());
	com.puresoltechnologies.purifinity.server.accountmanager.core.api.User userNew = new com.puresoltechnologies.purifinity.server.accountmanager.core.api.User(
		new EmailAddress(user.getEmail()), user.getName(), role);
	accountManager.setUser(new EmailAddress(email), userNew);
    }

    @Override
    public User getUser(String email) {
	com.puresoltechnologies.purifinity.server.accountmanager.core.api.User user = accountManager
		.getUser(new EmailAddress(email));
	return new User(user.getEmail().getAddress(), user.getName(), new Role(
		user.getRole().getId(), user.getRole().getName()));
    }

    @Override
    public String createAccount(String email, String password)
	    throws PasswordCreationException {
	return accountManager.createPassword(new EmailAddress(email),
		new Password(password));
    }

    @Override
    public String activateAccount(String email, String activationKey)
	    throws PasswordActivationException {
	return accountManager.activatePassword(new EmailAddress(email),
		activationKey).getAddress();
    }

    @Override
    public void removeAccount(String email) {
	accountManager.removePassword(new EmailAddress(email));
    }

    @Override
    public boolean changePassword(String email, String oldPassword,
	    String newPassword) throws PasswordChangeException {
	return accountManager.changePassword(new EmailAddress(email),
		new Password(oldPassword), new Password(newPassword));
    }

    @Override
    public String resetPassword(String email) throws PasswordResetException {
	return accountManager.resetPassword(new EmailAddress(email))
		.getPassword();
    }

}
