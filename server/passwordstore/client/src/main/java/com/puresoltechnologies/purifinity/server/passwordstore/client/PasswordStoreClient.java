package com.puresoltechnologies.purifinity.server.passwordstore.client;

import java.io.Closeable;

import javax.ws.rs.NotAcceptableException;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.passwordstore.core.api.PasswordStore;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.api.PasswordActivationEntity;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.api.PasswordAuthenticationEntity;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.api.PasswordChangeEntity;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.api.PasswordCreationEntity;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.api.PasswordStoreRestInterface;

/**
 * This is the main client for the PasswordStore. This client should not use
 * injection due to the use in JAAS LoginModule which is not instantiated by a
 * container.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PasswordStoreClient implements PasswordStore, Closeable {

    private final PasswordStoreRestInterface proxy;

    static {
	RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
    }

    private final ResteasyClient client;

    public PasswordStoreClient() {
	client = new ResteasyClientBuilder().build();
	ResteasyWebTarget webTarget = client
		.target("http://localhost:8080/passwordstore");
	proxy = webTarget.proxy(PasswordStoreRestInterface.class);
    }

    @Override
    public void close() {
	client.close();
    }

    @Override
    public String createPassword(EmailAddress email, Password password)
	    throws PasswordCreationException {
	try {
	    PasswordCreationEntity passwordCreationEntity = new PasswordCreationEntity(
		    email.getAddress(), password.getPassword());
	    return proxy.createPassword(passwordCreationEntity);
	} catch (NotAcceptableException e) {
	    throw new PasswordCreationException("Could not create password.", e);
	}
    }

    @Override
    public boolean authenticate(EmailAddress email, Password password) {
	return proxy.authenticate(new PasswordAuthenticationEntity(email
		.getAddress(), password.getPassword()));
    }

    @Override
    public EmailAddress activatePassword(EmailAddress email,
	    String activationKey) throws PasswordActivationException {
	try {
	    return proxy.activatePassword(new PasswordActivationEntity(email,
		    activationKey));
	} catch (NotAcceptableException e) {
	    throw new PasswordActivationException(
		    "Could not activate password.", e);
	}
    }

    @Override
    public boolean changePassword(EmailAddress email, Password oldPassword,
	    Password newPassword) throws PasswordChangeException {
	try {
	    return proxy.changePassword(new PasswordChangeEntity(email,
		    oldPassword, newPassword));
	} catch (NotAcceptableException e) {
	    throw new PasswordChangeException("Could not change password.", e);
	}
    }

    @Override
    public Password resetPassword(EmailAddress email)
	    throws PasswordResetException {
	try {
	    return proxy.resetPassword(email.getAddress());
	} catch (NotAcceptableException e) {
	    throw new PasswordResetException("Could not reset password.", e);
	}
    }

    @Override
    public void deletePassword(EmailAddress emailAddress) {
	proxy.deletePassword(emailAddress.getAddress());
    }
}
