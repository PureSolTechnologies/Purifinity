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

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security.AuthElement;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.Event;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventSeverity;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventType;

/**
 * This class is an intercepter for REST services which checks for
 * authentication and authorization.
 * 
 * The security for Web UI and AngularJS was described in several posts. The
 * links to these posts are below.
 * 
 * @see Web 
 *      http://bitsuppliers.com/securing-rest-resources-with-java-ee-7-and-jax
 *      -rs -2-0 /
 * @see Web 
 *      http://www.developerscrappad.com/1814/java/java-ee/rest-jax-rs/java-ee
 *      -7- jax
 *      -rs-2-0-simple-rest-api-authentication-authorization-with-custom-http-
 *      header /#sthash.ZvuVwQ2p.eaof
 * @see Web 
 *      http://www.aschua.de/blog/pairing-angularjs-and-javaee-for-authentication
 *      /
 * 
 * @author Rick-Rainer Ludwig
 *
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthSecurityIntercepter implements ContainerRequestFilter {

    // 401 - Access denied
    private static final Response ACCESS_UNAUTHORIZED = Response.status(
	    Response.Status.UNAUTHORIZED).build();
    // 403 - Forbidden
    private static final Response FORBIDDEN = Response.status(
	    Response.Status.UNAUTHORIZED).build();

    @Inject
    private AuthService authService;

    @Context
    private HttpServletRequest request;

    @Context
    private ResourceInfo resourceInfo;

    @Inject
    private EventLogger eventLogger;

    @Override
    public void filter(ContainerRequestContext requestContext)
	    throws IOException {
	// Get AuthId and AuthToken from HTTP-Header.
	EmailAddress email = null;
	String emailAddressString = requestContext
		.getHeaderString(AuthElement.AUTH_ID_HEADER);
	if (emailAddressString != null) {
	    email = new EmailAddress(emailAddressString);
	}
	String authTokenString = requestContext
		.getHeaderString(AuthElement.AUTH_TOKEN_HEADER);
	// Get method invoked.
	Method methodInvoked = resourceInfo.getResourceMethod();
	if (methodInvoked.isAnnotationPresent(PermitAll.class)) {
	    return;
	} else {
	    if ((email == null) || (email.getAddress() == null)
		    || (email.getAddress().isEmpty())
		    || (authTokenString == null) || (authTokenString.isEmpty())) {
		requestContext.abortWith(ACCESS_UNAUTHORIZED);
		eventLogger.logEvent(new Event("Authentication", 0,
			EventType.SYSTEM, EventSeverity.WARNING,
			"User was not authenticated, yet."));
		return;
	    }
	    UUID authToken;
	    try {
		authToken = UUID.fromString(authTokenString);
	    } catch (IllegalArgumentException e) {
		eventLogger.logEvent(new Event("Authentication", 1,
			EventType.SYSTEM, EventSeverity.WARNING,
			"Invalid user token '" + authTokenString
				+ "' for user '" + email + "'."));
		requestContext.abortWith(ACCESS_UNAUTHORIZED);
		return;
	    }
	    if (methodInvoked.isAnnotationPresent(RolesAllowed.class)) {

		RolesAllowed rolesAllowedAnnotation = methodInvoked
			.getAnnotation(RolesAllowed.class);
		Set<String> rolesAllowed = new HashSet<>(
			Arrays.asList(rolesAllowedAnnotation.value()));

		if (!authService.isAuthorized(email, authToken, rolesAllowed)) {
		    eventLogger.logEvent(new Event("Authentication", 1,
			    EventType.SYSTEM, EventSeverity.WARNING, "User '"
				    + email + "' is not authorized for '"
				    + methodInvoked.getDeclaringClass() + "."
				    + methodInvoked.getName() + "'."));
		    requestContext.abortWith(ACCESS_UNAUTHORIZED);
		}
		return;
	    } else if (authService.isAuthorizedAdministrator(email, authToken)) {
		return;
	    } else if (methodInvoked.isAnnotationPresent(DenyAll.class)) {
		eventLogger.logEvent(new Event("Authentication", 1,
			EventType.SYSTEM, EventSeverity.WARNING, "User '"
				+ email + "' is not authorized for '"
				+ methodInvoked.getDeclaringClass() + "."
				+ methodInvoked.getName() + "'."));
		requestContext.abortWith(FORBIDDEN);
		return;
	    }
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