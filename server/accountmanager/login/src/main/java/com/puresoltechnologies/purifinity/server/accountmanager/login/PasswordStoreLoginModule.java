package com.puresoltechnologies.purifinity.server.accountmanager.login;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManagerPrincipalImpl;
import com.puresoltechnologies.purifinity.server.passwordstore.client.CallerPrincipalGroup;
import com.puresoltechnologies.purifinity.server.passwordstore.client.NamePrincipal;
import com.puresoltechnologies.purifinity.server.passwordstore.client.PasswordStoreClient;
import com.puresoltechnologies.purifinity.server.passwordstore.client.RolesGroup;

/**
 * This is the login module for the PasswordStore. This class contains the JAAS
 * conform implementation of the {@link LoginModule} interface.
 * 
 * Have a look to:
 * 
 * http://www.radcortez.com/custom-principal-and-loginmodule-for-wildfly/
 * 
 * Additionally, these posts were helpful:
 * 
 * http://
 * docs.jboss.org/jbosssecurity/docs/6.0/security_guide/html/Login_Modules.html
 * 
 * http://docs.jboss.org/jbossas/docs/Server_Configuration_Guide/4/html/
 * Writing_Custom_Login_Modules-A_Custom_LoginModule_Example.html
 * 
 * http://www.pramati.com/docstore/1270002/index.htm
 * 
 * http://www.edc4it.com/2011/05
 * /13/understanding-java-security-and-jaas-part-3-a-custom-login-module/
 * 
 * @author Rick-Rainer Ludwig
 */
public class PasswordStoreLoginModule implements LoginModule {

    private static final Logger logger = LoggerFactory
	    .getLogger(PasswordStoreLoginModule.class);

    private Subject subject = null;
    private CallbackHandler callbackHander = null;
    private Map<String, ?> sharedState = null;
    private Map<String, ?> options = null;
    private boolean loggedIn = false;

    private final NamePrincipal userPrincipal = new NamePrincipal("User");
    private final RolesGroup rolesGroup = new RolesGroup();
    private final CallerPrincipalGroup callerPrincipalGroup = new CallerPrincipalGroup();
    private AccountManagerPrincipalImpl userNamePrincipal;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler,
	    Map<String, ?> sharedState, Map<String, ?> options) {
	this.subject = subject;
	this.callbackHander = callbackHandler;
	this.sharedState = sharedState;
	this.options = options;
    }

    @Override
    public boolean login() throws LoginException {
	try {
	    loggedIn = false;
	    NameCallback nameCallback = new NameCallback("Email");
	    PasswordCallback passwordCallback = new PasswordCallback(
		    "Password", false);
	    Callback[] callbacks = new Callback[] { nameCallback,
		    passwordCallback };
	    callbackHander.handle(callbacks);
	    EmailAddress email = new EmailAddress(nameCallback.getName());
	    Password password = new Password(passwordCallback.getPassword()
		    .toString());
	    try (PasswordStoreClient passwordStore = new PasswordStoreClient()) {
		loggedIn = passwordStore.authenticate(email, password);
		if (loggedIn) {
		    userNamePrincipal = new AccountManagerPrincipalImpl(email);
		} else {
		    userNamePrincipal = null;
		}
		return loggedIn;
	    }
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    throw new LoginException("IO exception occured!");
	} catch (UnsupportedCallbackException e) {
	    logger.error(e.getMessage(), e);
	    throw new LoginException("Unsupported callback used!");
	}
    }

    @Override
    public boolean commit() throws LoginException {
	Set<Principal> principals = subject.getPrincipals();
	if (loggedIn) {
	    principals.add(userPrincipal);
	    principals.add(rolesGroup);
	    rolesGroup.addMember(userPrincipal);
	    principals.add(callerPrincipalGroup);
	    callerPrincipalGroup.addMember(userNamePrincipal);
	} else {
	    principals.remove(userPrincipal);
	    principals.remove(rolesGroup);
	    rolesGroup.removeMember(userPrincipal);
	    principals.remove(callerPrincipalGroup);
	    callerPrincipalGroup.removeMember(userNamePrincipal);
	}
	return true;
    }

    @Override
    public boolean abort() throws LoginException {
	return true;
    }

    @Override
    public boolean logout() throws LoginException {
	Set<Principal> principals = subject.getPrincipals();
	principals.remove(userPrincipal);
	principals.remove(rolesGroup);
	rolesGroup.removeMember(userPrincipal);
	principals.remove(callerPrincipalGroup);
	callerPrincipalGroup.removeMember(userNamePrincipal);
	return true;
    }

}
