package com.puresoltechnologies.purifinity.server.passwordstore.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.jboss.resteasy.core.ServerResponse;

import com.puresoltechnologies.purifinity.server.passwordstore.core.api.PasswordStore;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.PasswordStoreHttpConstants;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.PasswordStoreRestInterface;

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
						.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_ID,
								"Invalid parameter!").build();
			}
			String activationKey = passwordStore.createAccount(splits[0],
					splits[1]);
			ResponseBuilder response = ServerResponse
					.status(HttpStatus.SC_CREATED);
			return response
					.header(HttpHeaders.CONTENT_TYPE, "text/plain")
					.header(PasswordStoreHttpConstants.HTTP_HEADER_ACTIVATION_KEY,
							activationKey).build();
		} catch (AccountCreationException e) {
			if (e.getEventId() > 0) {
				return Response
						.status(HttpStatus.SC_NOT_ACCEPTABLE)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_ID,
								e.getEventId())
						.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_MESSAGE,
								e.getMessage()).build();
			} else {
				return Response
						.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_ID,
								e.getEventId())
						.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_MESSAGE,
								e.getMessage()).build();
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
						.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_ID,
								"Invalid parameter!").build();
			}
			String email = passwordStore.activateAccount(splits[0], splits[1]);
			ResponseBuilder response = ServerResponse.ok();
			return response
					.status(HttpStatus.SC_OK)
					.header(HttpHeaders.CONTENT_TYPE, "text/plain")
					.header(PasswordStoreHttpConstants.HTTP_HEADER_USER_EMAIL,
							email).build();
		} catch (AccountActivationException e) {
			if (e.getEventId() > 0) {
				return Response
						.status(HttpStatus.SC_NOT_ACCEPTABLE)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_ID,
								e.getEventId())
						.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_MESSAGE,
								e.getMessage()).build();
			} else {
				return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header("message", e.getEventId())
						.header("exception-message", e.getMessage()).build();
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
					.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_ID,
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
						.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_ID,
								"Invalid parameter!").build();
			}
			boolean changed = passwordStore.changePassword(splits[0],
					splits[1], splits[2]);
			if (changed) {
				ResponseBuilder response = ServerResponse
						.status(HttpStatus.SC_OK);
				return response
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreHttpConstants.HTTP_HEADER_ACTIVATION_KEY,
								changed).build();
			} else {
				ResponseBuilder response = ServerResponse
						.status(HttpStatus.SC_UNAUTHORIZED);
				return response
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreHttpConstants.HTTP_HEADER_ACTIVATION_KEY,
								changed).build();
			}
		} catch (PasswordChangeException e) {
			if (e.getEventId() > 0) {
				return Response
						.status(HttpStatus.SC_NOT_ACCEPTABLE)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_ID,
								e.getEventId())
						.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_MESSAGE,
								e.getMessage()).build();
			} else {
				return Response
						.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_ID,
								e.getEventId())
						.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_MESSAGE,
								e.getMessage()).build();
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
					.header(PasswordStoreHttpConstants.HTTP_HEADER_NEW_PASSWORD,
							activationKey).build();
		} catch (PasswordResetException e) {
			if (e.getEventId() > 0) {
				return Response
						.status(HttpStatus.SC_NOT_ACCEPTABLE)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_ID,
								e.getEventId())
						.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_MESSAGE,
								e.getMessage()).build();
			} else {
				return Response
						.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
						.header(HttpHeaders.CONTENT_TYPE, "text/plain")
						.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_ID,
								e.getEventId())
						.header(PasswordStoreHttpConstants.HTTP_HEADER_EVENT_MESSAGE,
								e.getMessage()).build();
			}
		}
	}
}