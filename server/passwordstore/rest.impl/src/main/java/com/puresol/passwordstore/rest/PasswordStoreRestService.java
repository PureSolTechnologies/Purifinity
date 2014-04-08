package com.puresol.passwordstore.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpHeaders;
import org.jboss.resteasy.core.ServerResponse;

import com.puresol.commons.utils.ExceptionMessage;
import com.puresol.passwordstore.core.api.PasswordStore;
import com.puresol.passwordstore.domain.AccountActivationException;
import com.puresol.passwordstore.domain.AccountCreationException;
import com.puresol.passwordstore.domain.PasswordChangeException;
import com.puresol.passwordstore.domain.PasswordResetException;
import com.puresol.passwordstore.domain.PasswordStoreExceptionMessage;

public class PasswordStoreRestService implements PasswordStoreRestInterface {

	@Inject
	private PasswordStore passwordStore;

	@Override
	public Response createAccount(String content) {
		try {
			String[] splits = content.split("\n");
			if (splits.length != 2) {
				return Response
						.status(HttpStatus.SC_BAD_REQUEST)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreConstants.HTTP_HEADER_MESSAGE,
								"Invalid parameter!").build();
			}
			String activationKey = passwordStore.createAccount(splits[0],
					splits[1]);
			ResponseBuilder response = ServerResponse
					.status(HttpStatus.SC_CREATED);
			return response
					.header(HttpHeaders.CONTENT_TYPE, "text/plain")
					.header(PasswordStoreConstants.HTTP_HEADER_ACTIVATION_KEY,
							activationKey).build();
		} catch (AccountCreationException e) {
			ExceptionMessage exceptionMessage = e.getExceptionMessage();
			if (exceptionMessage == PasswordStoreExceptionMessage.INVALID_EMAIL_ADDRESS) {
				return Response
						.status(HttpStatus.SC_NOT_ACCEPTABLE)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreConstants.HTTP_HEADER_MESSAGE,
								exceptionMessage.getId())
						.header(PasswordStoreConstants.HTTP_HEADER_EXCEPTION_MESSAGE,
								exceptionMessage.getClass()).build();
			} else if (exceptionMessage == PasswordStoreExceptionMessage.PASSWORD_TOO_WEAK) {
				return Response
						.status(HttpStatus.SC_NOT_ACCEPTABLE)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreConstants.HTTP_HEADER_MESSAGE,
								exceptionMessage.getId())
						.header(PasswordStoreConstants.HTTP_HEADER_EXCEPTION_MESSAGE,
								exceptionMessage.getClass()).build();
			} else if (exceptionMessage == PasswordStoreExceptionMessage.ACCOUNT_ALREADY_EXISTS) {
				return Response
						.status(HttpStatus.SC_NOT_ACCEPTABLE)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreConstants.HTTP_HEADER_MESSAGE,
								exceptionMessage.getId())
						.header(PasswordStoreConstants.HTTP_HEADER_EXCEPTION_MESSAGE,
								exceptionMessage.getClass()).build();
			} else {
				return Response
						.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreConstants.HTTP_HEADER_MESSAGE,
								exceptionMessage.getId())
						.header(PasswordStoreConstants.HTTP_HEADER_EXCEPTION_MESSAGE,
								exceptionMessage.getClass()).build();
			}
		}
	}

	@Override
	public Response activateAccount(String content) {
		try {
			String[] splits = content.split("\n");
			if (splits.length != 2) {
				return Response
						.status(HttpStatus.SC_BAD_REQUEST)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreConstants.HTTP_HEADER_MESSAGE,
								"Invalid parameter!").build();
			}
			long userId = passwordStore.activateAccount(splits[0], splits[1]);
			ResponseBuilder response = ServerResponse.ok();
			return response
					.status(HttpStatus.SC_OK)
					.header(HttpHeaders.CONTENT_TYPE, "text/plain")
					.header(PasswordStoreConstants.HTTP_HEADER_USER_ID,
							String.valueOf(userId)).build();
		} catch (AccountActivationException e) {
			ExceptionMessage exceptionMessage = e.getExceptionMessage();
			if (exceptionMessage == PasswordStoreExceptionMessage.INVALID_ACTIVATION_KEY) {
				return Response
						.status(HttpStatus.SC_NOT_ACCEPTABLE)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreConstants.HTTP_HEADER_MESSAGE,
								exceptionMessage.getId())
						.header(PasswordStoreConstants.HTTP_HEADER_EXCEPTION_MESSAGE,
								exceptionMessage.getClass()).build();
			} else {
				return Response
						.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header("message", exceptionMessage.getId())
						.header("exception-message",
								exceptionMessage.getClass()).build();
			}
		}
	}

	@Override
	public Response authenticate(String content) {
		String[] splits = content.split("\n");
		if (splits.length != 2) {
			return Response
					.status(HttpStatus.SC_BAD_REQUEST)
					.header(HttpHeaders.CONTENT_TYPE, "text/plain")
					.header(PasswordStoreConstants.HTTP_HEADER_MESSAGE,
							"Invalid parameter!").build();
		}
		boolean authenticated = passwordStore
				.authenticate(splits[0], splits[1]);
		if (authenticated) {
			ResponseBuilder response = ServerResponse.ok();
			return response.status(HttpStatus.SC_OK)
					.header(HttpHeaders.CONTENT_TYPE, "text/plain").build();
		}
		ResponseBuilder response = ServerResponse
				.status(HttpStatus.SC_UNAUTHORIZED);
		return response.header(HttpHeaders.CONTENT_TYPE, "text/plain").build();
	}

	@Override
	public Response changePassword(String content) {
		try {
			String[] splits = content.split("\n");
			if (splits.length != 3) {
				return Response
						.status(HttpStatus.SC_BAD_REQUEST)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreConstants.HTTP_HEADER_MESSAGE,
								"Invalid parameter!").build();
			}
			boolean changed = passwordStore.changePassword(splits[0],
					splits[1], splits[2]);
			if (changed) {
				ResponseBuilder response = ServerResponse
						.status(HttpStatus.SC_OK);
				return response
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreConstants.HTTP_HEADER_ACTIVATION_KEY,
								changed).build();
			} else {
				ResponseBuilder response = ServerResponse
						.status(HttpStatus.SC_UNAUTHORIZED);
				return response
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreConstants.HTTP_HEADER_ACTIVATION_KEY,
								changed).build();
			}
		} catch (PasswordChangeException e) {
			ExceptionMessage exceptionMessage = e.getExceptionMessage();
			if (exceptionMessage == PasswordStoreExceptionMessage.PASSWORD_TOO_WEAK) {
				return Response
						.status(HttpStatus.SC_NOT_ACCEPTABLE)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreConstants.HTTP_HEADER_MESSAGE,
								exceptionMessage.getId())
						.header(PasswordStoreConstants.HTTP_HEADER_EXCEPTION_MESSAGE,
								exceptionMessage.getClass()).build();
			} else {
				return Response
						.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreConstants.HTTP_HEADER_MESSAGE,
								exceptionMessage.getId())
						.header(PasswordStoreConstants.HTTP_HEADER_EXCEPTION_MESSAGE,
								exceptionMessage.getClass()).build();
			}
		}
	}

	@Override
	@POST
	@Path("resetPassword")
	@Consumes("text/plain")
	@Produces("text/plain")
	public Response resetPassword(String content) {
		try {
			String activationKey = passwordStore.resetPassword(content);
			ResponseBuilder response = ServerResponse.status(HttpStatus.SC_OK);
			return response
					.header(HttpHeaders.CONTENT_TYPE, "text/plain")
					.header(PasswordStoreConstants.HTTP_HEADER_NEW_PASSWORD,
							activationKey).build();
		} catch (PasswordResetException e) {
			ExceptionMessage exceptionMessage = e.getExceptionMessage();
			if (exceptionMessage == PasswordStoreExceptionMessage.UNKNOWN_EMAIL_ADDRESS) {
				return Response
						.status(HttpStatus.SC_NOT_ACCEPTABLE)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreConstants.HTTP_HEADER_MESSAGE,
								exceptionMessage.getId())
						.header(PasswordStoreConstants.HTTP_HEADER_EXCEPTION_MESSAGE,
								exceptionMessage.getClass()).build();
			} else {
				return Response
						.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreConstants.HTTP_HEADER_MESSAGE,
								exceptionMessage.getId())
						.header(PasswordStoreConstants.HTTP_HEADER_EXCEPTION_MESSAGE,
								exceptionMessage.getClass()).build();
			}
		}
	}
}
