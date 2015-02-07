package com.puresoltechnologies.purifinity.server.accountmanager.core.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.buschmais.xo.api.Query.Result;
import com.buschmais.xo.api.ResultIterable;
import com.buschmais.xo.api.XOException;
import com.buschmais.xo.api.XOManager;
import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManager;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManagerRemote;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.Role;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.User;
import com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo.RoleVertex;
import com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo.UserVertex;
import com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo.UsersXOManager;
import com.puresoltechnologies.purifinity.server.accountmanager.domain.AccountManagerEvents;
import com.puresoltechnologies.purifinity.server.passwordstore.client.PasswordStoreClient;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;

@Stateful
@Local(AccountManager.class)
@Remote(AccountManagerRemote.class)
public class AccountManagerBean implements Serializable, AccountManager,
	AccountManagerRemote {

    private static final long serialVersionUID = 2254178680686589373L;

    @Inject
    private Logger logger;

    @Inject
    private EventLoggerRemote eventLogger;

    @Resource
    private SessionContext context;

    @Inject
    @UsersXOManager
    private XOManager xoManager;

    private final PasswordStoreClient passwordStore = new PasswordStoreClient();

    @Override
    public String createPassword(EmailAddress email, Password password)
	    throws PasswordCreationException {
	return passwordStore.createPassword(email, password);
    }

    @Override
    public EmailAddress activatePassword(EmailAddress email,
	    String activationKey) throws PasswordActivationException {
	EmailAddress userId = passwordStore.activatePassword(email,
		activationKey);
	createAccount(email);
	return userId;
    }

    @Override
    public boolean authenticate(EmailAddress email, Password password) {
	return passwordStore.authenticate(email, password);
    }

    @Override
    public boolean changePassword(EmailAddress email, Password oldPassword,
	    Password newPassword) throws PasswordChangeException {
	return passwordStore.changePassword(email, oldPassword, newPassword);
    }

    @Override
    public Password resetPassword(EmailAddress email)
	    throws PasswordResetException {
	return passwordStore.resetPassword(email);
    }

    @Override
    public EmailAddress getEmail() {
	Principal principal = context.getCallerPrincipal();
	return new EmailAddress(principal.getName());
    }

    @Override
    public String getName() {
	try {
	    Principal principal = context.getCallerPrincipal();
	    Method method = principal.getClass().getMethod("getRealName");
	    Object name = method.invoke(principal);
	    return name.toString();
	} catch (ClassCastException e) {
	    e.printStackTrace();
	    return "unauthenticated";
	} catch (SecurityException e) {
	    e.printStackTrace();
	    return "unauthenticated";
	} catch (NoSuchMethodException e) {
	    return "unauthenticated";
	} catch (IllegalArgumentException e) {
	    e.printStackTrace();
	    return "unauthenticated";
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	    return "unauthenticated";
	} catch (InvocationTargetException e) {
	    e.printStackTrace();
	    return "unauthenticated";
	}
    }

    @Override
    public boolean isLoggedIn() {
	try {
	    Principal principal = context.getCallerPrincipal();
	    Method method = principal.getClass().getMethod("getRealName");
	    Object name = method.invoke(principal);
	    return !(name.toString().isEmpty());
	} catch (ClassCastException e) {
	    e.printStackTrace();
	    return false;
	} catch (SecurityException e) {
	    e.printStackTrace();
	    return false;
	} catch (NoSuchMethodException e) {
	    return false;
	} catch (IllegalArgumentException e) {
	    e.printStackTrace();
	    return false;
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	    return false;
	} catch (InvocationTargetException e) {
	    e.printStackTrace();
	    return false;
	}
    }

    @Override
    public void createAccount(EmailAddress email) {
	logger.debug("Creating user account for '" + email + "'...");
	xoManager.currentTransaction().begin();
	try {
	    UserVertex user = xoManager.create(UserVertex.class);
	    user.setCreationTime(new Date());
	    user.setEmail(email.getAddress());
	    xoManager.currentTransaction().commit();
	    eventLogger.logEvent(AccountManagerEvents
		    .createAccountCreationEvent(email));
	} catch (XOException e) {
	    xoManager.currentTransaction().rollback();
	    throw e;
	}
    }

    @Override
    public Set<Role> getRoles() {
	Set<Role> roles = new LinkedHashSet<>();
	xoManager.currentTransaction().begin();
	try {
	    Result<RoleVertex> results = xoManager.createQuery(
		    "_().has('_xo_discriminator_" + RoleVertex.NAME + "')",
		    RoleVertex.class).execute();
	    for (RoleVertex roleVertex : results) {
		roles.add(new Role(roleVertex.getId(), roleVertex.getName()));
	    }
	} finally {
	    xoManager.currentTransaction().rollback();
	}
	return roles;
    }

    @Override
    public Set<User> getUsers() {
	Set<User> users = new LinkedHashSet<>();
	xoManager.currentTransaction().begin();
	try {
	    Result<UserVertex> results = xoManager.createQuery(
		    "_().has('_xo_discriminator_" + UserVertex.NAME + "')",
		    UserVertex.class).execute();
	    for (UserVertex userVertex : results) {
		RoleVertex roleVertex = userVertex.getRole();
		Role role = new Role(roleVertex.getId(), roleVertex.getName());
		User user = new User(new EmailAddress(userVertex.getEmail()),
			userVertex.getName(), role);
		users.add(user);
	    }
	} finally {
	    xoManager.currentTransaction().rollback();
	}
	return users;
    }

    @Override
    public void setUser(EmailAddress email, User user) {
	xoManager.currentTransaction().begin();
	try {
	    UserVertex userVertex = xoManager.find(UserVertex.class,
		    user.getEmail()).getSingleResult();
	    userVertex.setName(user.getName());
	    xoManager.currentTransaction().commit();
	} catch (XOException e) {
	    xoManager.currentTransaction().rollback();
	    throw e;
	}
    }

    @Override
    public User getUser(EmailAddress email) {
	xoManager.currentTransaction().begin();
	try {
	    UserVertex userVertex = xoManager.find(UserVertex.class,
		    email.getAddress()).getSingleResult();
	    RoleVertex roleVertex = userVertex.getRole();
	    Role role = new Role(roleVertex.getId(), roleVertex.getName());
	    return new User(new EmailAddress(userVertex.getEmail()),
		    userVertex.getName(), role);
	} finally {
	    xoManager.currentTransaction().rollback();
	}
    }

    @Override
    public void removePassword(EmailAddress email) {
	passwordStore.removePassword(email);
	ResultIterable<UserVertex> userVertex = xoManager.find(
		UserVertex.class, email.getAddress());
	xoManager.delete(userVertex);
    }
}
