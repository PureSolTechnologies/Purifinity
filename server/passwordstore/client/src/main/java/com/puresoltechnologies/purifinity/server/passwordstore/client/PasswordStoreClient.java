package com.puresoltechnologies.purifinity.server.passwordstore.client;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
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
public class PasswordStoreClient {

    private final PasswordStoreRestInterface proxy;

    static {
	RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
    }

    public static PasswordStoreClient createInstance() {
	return new PasswordStoreClient();
    }

    public PasswordStoreClient() {
	ResteasyClient client = new ResteasyClientBuilder().build();
	ResteasyWebTarget webTarget = client
		.target("http://localhost:8080/passwordstore");
	proxy = webTarget.proxy(PasswordStoreRestInterface.class);
    }

    public String createAccount(EmailAddress email, Password password)
	    throws PasswordCreationException {
	PasswordCreationEntity passwordCreationEntity = new PasswordCreationEntity(
		email.getAddress(), password.getPassword());
	return proxy.createPassword(passwordCreationEntity);
    }

    public boolean authenticate(EmailAddress email, Password password) {
	return proxy.authenticate(new PasswordAuthenticationEntity(email
		.getAddress(), password.getPassword()));
    }

    public EmailAddress activatePassword(EmailAddress email,
	    String activationKey) throws PasswordActivationException {
	return proxy.activatePassword(new PasswordActivationEntity(email,
		activationKey));
    }

    public boolean changePassword(EmailAddress email, Password oldPassword,
	    Password newPassword) throws PasswordChangeException {
	return proxy.changePassword(new PasswordChangeEntity(email,
		oldPassword, newPassword));
    }

    public Password resetPassword(EmailAddress email)
	    throws PasswordResetException {
	return proxy.resetPassword(email.getAddress());
    }

    public void deletePassword(String emailAddress) {
	proxy.deletePassword(emailAddress);
    }
}
