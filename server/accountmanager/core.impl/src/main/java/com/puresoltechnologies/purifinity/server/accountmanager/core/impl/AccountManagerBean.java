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
import com.buschmais.xo.api.XOException;
import com.buschmais.xo.api.XOManager;
import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManager;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManagerRemote;
import com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo.RoleVertex;
import com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo.UserVertex;
import com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo.UsersXOManager;
import com.puresoltechnologies.purifinity.server.accountmanager.domain.AccountManagerEvents;
import com.puresoltechnologies.purifinity.server.accountmanager.domain.Role;
import com.puresoltechnologies.purifinity.server.accountmanager.domain.User;
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
	return passwordStore.createAccount(email, password);
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
	UserVertex user = xoManager.create(UserVertex.class);
	user.setCreationTime(new Date());
	user.setEmail(email.getAddress());
	xoManager.currentTransaction().commit();
	eventLogger.logEvent(AccountManagerEvents
		.createAccountCreationEvent(email));
    }

    @Override
    public Set<Role> getRoles() {
	Result<RoleVertex> results = xoManager.createQuery(RoleVertex.class)
		.execute();
	Set<Role> roles = new LinkedHashSet<>();
	for (RoleVertex roleVertex : results) {
	    roles.add(new Role(roleVertex.getRoleId(), roleVertex.getName()));
	}
	return roles;
    }

    @Override
    public Set<User> getUsers() {
	Result<UserVertex> results = xoManager.createQuery(UserVertex.class)
		.execute();
	Set<User> users = new LinkedHashSet<>();
	for (UserVertex userVertex : results) {
	    users.add(new User(userVertex.getEmail(), userVertex.getName()));
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
	}
    }

    @Override
    public User getUser(EmailAddress email) {
	UserVertex userVertex = xoManager.find(UserVertex.class,
		email.getAddress()).getSingleResult();
	return new User(userVertex.getEmail(), userVertex.getName());
    }

}
