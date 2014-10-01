package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl.security;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security.AuthElement;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthSecurityInterceptor implements ContainerRequestFilter {

	// 401 - Access denied
	private static final Response ACCESS_UNAUTHORIZED = Response
			.status(Response.Status.UNAUTHORIZED).entity("Not authorized.")
			.build();
	// 403 - Forbidden
	private static final Response FORBIDDEN = Response
			.status(Response.Status.UNAUTHORIZED).entity("Not allowed.")
			.build();
	// 403 - Forbidden
	private static final Response BAD_REQUEST = Response
			.status(Response.Status.BAD_REQUEST)
			.entity("Invalid request header.").build();

	@Inject
	private AuthService authService;

	@Context
	private HttpServletRequest request;

	@Context
	private ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		// Get AuthId and AuthToken from HTTP-Header.
		String authId = requestContext
				.getHeaderString(AuthElement.AUTH_ID_HEADER);
		String authTokenString = requestContext
				.getHeaderString(AuthElement.AUTH_TOKEN_HEADER);
		if ((authId == null) || (authTokenString == null)) {
			requestContext.abortWith(ACCESS_UNAUTHORIZED);
			return;
		}
		UUID authToken;
		try {
			authToken = UUID.fromString(authTokenString);
		} catch (IllegalArgumentException e) {
			requestContext.abortWith(BAD_REQUEST);
			return;
		}

		// Get method invoked.
		Method methodInvoked = resourceInfo.getResourceMethod();
		if (methodInvoked.isAnnotationPresent(PermitAll.class)) {
			return;
		} else if (methodInvoked.isAnnotationPresent(RolesAllowed.class)) {

			RolesAllowed rolesAllowedAnnotation = methodInvoked
					.getAnnotation(RolesAllowed.class);
			Set<String> rolesAllowed = new HashSet<>(
					Arrays.asList(rolesAllowedAnnotation.value()));

			if (!authService.isAuthorized(authId, authToken, rolesAllowed)) {
				requestContext.abortWith(ACCESS_UNAUTHORIZED);
			}
			return;
		} else if (authService.isAuthorizedAdministrator(authId, authToken)) {
			return;
		} else if (methodInvoked.isAnnotationPresent(DenyAll.class)) {
			requestContext.abortWith(FORBIDDEN);
			return;
		}
		/*
		 * ATTENTION(!): DUE TO SECURITY REASONS, ALL REST METHODS ARE DENIED
		 * PER DEFAULT!
		 * 
		 * Functionality needs to be declared permitted or roles added to open
		 * methods into the public.
		 */
		requestContext.abortWith(ACCESS_UNAUTHORIZED);
	}
}