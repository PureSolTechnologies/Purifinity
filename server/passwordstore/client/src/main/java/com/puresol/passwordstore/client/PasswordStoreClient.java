package com.puresol.passwordstore.client;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.http.HttpStatus;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.puresol.passwordstore.domain.AccountActivationException;
import com.puresol.passwordstore.domain.AccountCreationException;
import com.puresol.passwordstore.domain.PasswordChangeException;
import com.puresol.passwordstore.domain.PasswordResetException;
import com.puresol.passwordstore.rest.PasswordStoreHttpConstants;
import com.puresol.passwordstore.rest.PasswordStoreRestInterface;

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
		proxy = ProxyFactory.create(PasswordStoreRestInterface.class,
				"http://localhost:8080/passwordstore");
	}

	public String createAccount(String email, String password)
			throws AccountCreationException {
		@SuppressWarnings("unchecked")
		ClientResponse<String> response = (ClientResponse<String>) proxy
				.createAccount(email + "\n" + password);
		try {
			if (response.getResponseStatus().getStatusCode() == HttpStatus.SC_CREATED) {
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
			response.releaseConnection();
		}
	}

	public boolean authenticate(String email, String password) {
		@SuppressWarnings("unchecked")
		ClientResponse<String> response = (ClientResponse<String>) proxy
				.authenticate(email + "\n" + password);
		try {
			if (response.getResponseStatus().getStatusCode() == HttpStatus.SC_OK) {
				return true;
			}
			return false;
		} finally {
			response.releaseConnection();
		}
	}

	public long activateAccount(String email, String activationKey)
			throws AccountActivationException {
		@SuppressWarnings("unchecked")
		ClientResponse<String> response = (ClientResponse<String>) proxy
				.activateAccount(email + "\n" + activationKey);
		try {
			if (response.getResponseStatus().getStatusCode() == HttpStatus.SC_OK) {
				List<Object> userIdList = response.getHeaders().get(
						PasswordStoreHttpConstants.HTTP_HEADER_USER_EMAIL);
				if (userIdList.size() == 0) {
					throw new RuntimeException(
							"The current OK response does not have a user-id header included!");
				}
				if (userIdList.size() > 1) {
					throw new RuntimeException(
							"The current OK response does have multiple user-id headers included!");
				}
				String userId = (String) userIdList.get(0);
				try {
					return Long.valueOf(userId);
				} catch (NumberFormatException e) {
					throw new RuntimeException(
							"The current OK response does have an invalid user-id '"
									+ userId
									+ "' (number format is wrong) included!", e);
				}
			}
			Object errorId = response.getHeaders().getFirst(
					PasswordStoreHttpConstants.HTTP_HEADER_EVENT_ID);
			Object errorMessage = response.getHeaders().getFirst(
					PasswordStoreHttpConstants.HTTP_HEADER_EVENT_MESSAGE);
			throw new AccountActivationException(
					Long.valueOf((String) errorId), (String) errorMessage);
		} finally {
			response.releaseConnection();
		}
	}

	public boolean changePassword(String email, String oldPassword,
			String newPassword) throws PasswordChangeException {
		@SuppressWarnings("unchecked")
		ClientResponse<String> response = (ClientResponse<String>) proxy
				.changePassword(email + "\n" + oldPassword + "\n" + newPassword);
		try {
			if (response.getResponseStatus().getStatusCode() == HttpStatus.SC_OK) {
				return true;
			} else if (response.getResponseStatus().getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
				return false;
			}
			Object errorId = response.getHeaders().getFirst(
					PasswordStoreHttpConstants.HTTP_HEADER_EVENT_ID);
			Object errorMessage = response.getHeaders().getFirst(
					PasswordStoreHttpConstants.HTTP_HEADER_EVENT_MESSAGE);
			throw new PasswordChangeException(Long.valueOf((String) errorId),
					(String) errorMessage);
		} finally {
			response.releaseConnection();
		}
	}

	public String resetPassword(String email) throws PasswordResetException {
		@SuppressWarnings("unchecked")
		ClientResponse<String> response = (ClientResponse<String>) proxy
				.resetPassword(email);
		try {
			if (response.getResponseStatus().getStatusCode() == HttpStatus.SC_OK) {
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
			response.releaseConnection();
		}
	}
}
