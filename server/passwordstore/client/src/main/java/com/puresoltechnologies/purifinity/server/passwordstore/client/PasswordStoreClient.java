package com.puresoltechnologies.purifinity.server.passwordstore.client;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.PasswordStoreHttpConstants;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.PasswordStoreRestInterface;

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

    public String createAccount(String email, String password)
	    throws AccountCreationException {
	Response response = proxy.createAccount(email + "\n" + password);
	try {
	    if (response.getStatus() == HttpStatus.SC_CREATED) {
		MultivaluedMap<String, Object> headers = response.getHeaders();
		return String
			.valueOf(headers
				.getFirst(PasswordStoreHttpConstants.HTTP_HEADER_ACTIVATION_KEY));
	    }
	    Object errorId = response.getHeaders().getFirst(
		    PasswordStoreHttpConstants.HTTP_HEADER_EVENT_ID);
	    Object errorMessage = response.getHeaders().getFirst(
		    PasswordStoreHttpConstants.HTTP_HEADER_EVENT_MESSAGE);
	    throw new AccountCreationException(Long.valueOf((String) errorId),
		    (String) errorMessage);
	} finally {
	    response.close();
	}
    }

    public boolean authenticate(String email, String password) {
	Response response = proxy.authenticate(email + "\n" + password);
	try {
	    if (response.getStatus() == HttpStatus.SC_OK) {
		return true;
	    }
	    return false;
	} finally {
	    response.close();
	}
    }

    public String activateAccount(String email, String activationKey)
	    throws AccountActivationException {
	Response response = proxy.activateAccount(email + "\n" + activationKey);
	try {
	    if (response.getStatus() == HttpStatus.SC_OK) {
		List<Object> userIdList = response.getHeaders().get(
			PasswordStoreHttpConstants.HTTP_HEADER_USER_EMAIL);
		if (userIdList.size() == 0) {
		    throw new RuntimeException(
			    "The current OK response does not have a '"
				    + PasswordStoreHttpConstants.HTTP_HEADER_USER_EMAIL
				    + "' header included!");
		}
		if (userIdList.size() > 1) {
		    throw new RuntimeException(
			    "The current OK response does have multiple '"
				    + PasswordStoreHttpConstants.HTTP_HEADER_USER_EMAIL
				    + "' headers included!");
		}
		return (String) userIdList.get(0);
	    }
	    Object errorId = response.getHeaders().getFirst(
		    PasswordStoreHttpConstants.HTTP_HEADER_EVENT_ID);
	    Object errorMessage = response.getHeaders().getFirst(
		    PasswordStoreHttpConstants.HTTP_HEADER_EVENT_MESSAGE);
	    throw new AccountActivationException(
		    Long.valueOf((String) errorId), (String) errorMessage);
	} finally {
	    response.close();
	}
    }

    public boolean changePassword(String email, String oldPassword,
	    String newPassword) throws PasswordChangeException {
	Response response = proxy.changePassword(email + "\n" + oldPassword
		+ "\n" + newPassword);
	try {
	    if (response.getStatus() == HttpStatus.SC_OK) {
		return true;
	    } else if (response.getStatus() == HttpStatus.SC_UNAUTHORIZED) {
		return false;
	    }
	    Object errorId = response.getHeaders().getFirst(
		    PasswordStoreHttpConstants.HTTP_HEADER_EVENT_ID);
	    Object errorMessage = response.getHeaders().getFirst(
		    PasswordStoreHttpConstants.HTTP_HEADER_EVENT_MESSAGE);
	    throw new PasswordChangeException(Long.valueOf((String) errorId),
		    (String) errorMessage);
	} finally {
	    response.close();
	}
    }

    public String resetPassword(String email) throws PasswordResetException {
	Response response = proxy.resetPassword(email);
	try {
	    if (response.getStatus() == HttpStatus.SC_OK) {
		MultivaluedMap<String, Object> headers = response.getHeaders();
		return (String) headers
			.getFirst(PasswordStoreHttpConstants.HTTP_HEADER_NEW_PASSWORD);
	    }
	    Object errorId = response.getHeaders().getFirst(
		    PasswordStoreHttpConstants.HTTP_HEADER_EVENT_ID);
	    Object errorMessage = response.getHeaders().getFirst(
		    PasswordStoreHttpConstants.HTTP_HEADER_EVENT_MESSAGE);
	    throw new PasswordResetException(Long.valueOf((String) errorId),
		    (String) errorMessage);
	} finally {
	    response.close();
	}
    }
}
