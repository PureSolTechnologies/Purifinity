package com.puresoltechnologies.purifinity.server.accountmanager.rest.impl;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.IllegalEmailAddressException;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManager;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManagerException;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.AccountManagerRestInterface;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.ChangePasswordEntity;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.CreateAccountEntity;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.EditAccountEntity;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.Role;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.User;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;

public class AccountManagerRestService implements AccountManagerRestInterface {

    @Inject
    private AccountManager accountManager;

    @Context
    private UriInfo uriInfo;

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
    public Response createAccount(CreateAccountEntity entity)
	    throws AccountManagerException {
	try {
	    EmailAddress emailAddress = new EmailAddress(entity.getEmail());
	    Password password = new Password(entity.getPassword());
	    String activationKey = accountManager.createPassword(emailAddress,
		    password);
	    accountManager.activatePassword(emailAddress, activationKey);
	    accountManager.createAccount(emailAddress, entity.getName(),
		    entity.getRoleId());
	    ResponseBuilder response = Response.created(uriInfo
		    .getAbsolutePathBuilder().path(emailAddress.getAddress())
		    .build());
	    return response.build();
	} catch (PasswordCreationException | PasswordActivationException e) {
	    throw new AccountManagerException("Could not create account '"
		    + entity.getEmail() + "'.", e);
	}
    }

    @Override
    public void alterAccount(String email, EditAccountEntity entity)
	    throws AccountManagerException {
	accountManager.alterAccount(new EmailAddress(email), entity.getName(),
		entity.getRoleId());
    }

    @Override
    public void removeAccount(String email) {
	accountManager.deletePassword(new EmailAddress(email));
    }

    @Override
    public boolean changePassword(String email, ChangePasswordEntity entity)
	    throws PasswordChangeException {
	try {
	    return accountManager.changePassword(new EmailAddress(email),
		    new Password(entity.getOldPassword()),
		    new Password(entity.getNewPassword()));
	} catch (IllegalEmailAddressException e) {
	    throw new PasswordChangeException("Could not change password.", e);
	}
    }

    @Override
    public String resetPassword(String email) throws PasswordResetException {
	return accountManager.resetPassword(new EmailAddress(email))
		.getPassword();
    }

}
