package com.puresoltechnologies.purifinity.server.common.rest.security;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.server.systemmonitor.core.api.events.Event;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventSeverity;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventType;

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
 * @see Web http://www.aschua.de/blog/pairing-angularjs-and-javaee-for-
 *      authentication /
 * @see Web http://stackoverflow.com/questions/3297048/403-forbidden-vs-401-
 *      unauthorized-http-responses
 * @author Rick-Rainer Ludwig
 *
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationAndAuthorizationFilter implements ContainerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationAndAuthorizationFilter.class);

    // 401 - Access denied
    private static final Response ACCESS_UNAUTHORIZED = Response.status(Response.Status.UNAUTHORIZED).build();
    // 403 - Forbidden
    private static final Response FORBIDDEN = Response.status(Response.Status.FORBIDDEN).build();

    @Inject
    private AuthService authService;

    @Context
    private HttpServletRequest request;

    @Context
    private ResourceInfo resourceInfo;

    @Inject
    private EventLoggerRemote eventLogger;

    @PostConstruct
    public void postConstruct() {
	logger.info("REST security is enabled.");
    }

    @PreDestroy
    public void preDestroy() {
	logger.info("REST security is disabled.");
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
	// Get method invoked.
	Method methodInvoked = resourceInfo.getResourceMethod();
	// Check if open method...
	if (methodInvoked.isAnnotationPresent(PermitAll.class)) {
	    // Method is globally permitted, so we proceed without interactions.
	    return;
	}
	// Get AuthId and AuthToken from HTTP-Header.
	String authIdString = requestContext.getHeaderString(AuthElement.AUTH_ID_HEADER);
	String authTokenString = requestContext.getHeaderString(AuthElement.AUTH_TOKEN_HEADER);
	if ((authIdString == null) || (authIdString.isEmpty()) || (authTokenString == null)
		|| (authTokenString.isEmpty())) {
	    requestContext.abortWith(ACCESS_UNAUTHORIZED);
	    eventLogger.logEvent(new Event("Authentication", 0, EventType.SYSTEM, EventSeverity.WARNING,
		    "User was not authenticated, yet."));
	    return;
	}
	// Check email address format and convert it...
	EmailAddress email;
	try {
	    email = new EmailAddress(authIdString);
	} catch (IllegalArgumentException e) {
	    eventLogger.logEvent(new Event("Authentication", 1, EventType.SYSTEM, EventSeverity.WARNING,
		    "Invalid email address '" + authIdString + "' provided."));
	    requestContext.abortWith(ACCESS_UNAUTHORIZED);
	    return;
	}
	// Check authentication token and convert it...
	UUID authToken;
	try {
	    authToken = UUID.fromString(authTokenString);
	} catch (IllegalArgumentException e) {
	    eventLogger.logEvent(new Event("Authentication", 1, EventType.SYSTEM, EventSeverity.WARNING,
		    "Invalid user token '" + authTokenString + "' for user '" + email + "'."));
	    requestContext.abortWith(ACCESS_UNAUTHORIZED);
	    return;
	}
	if (methodInvoked.isAnnotationPresent(RolesAllowed.class)) {
	    RolesAllowed rolesAllowedAnnotation = methodInvoked.getAnnotation(RolesAllowed.class);
	    Set<String> rolesAllowed = new HashSet<>(Arrays.asList(rolesAllowedAnnotation.roles()));

	    if (!authService.isAuthorized(email, authToken, rolesAllowed)) {
		eventLogger.logEvent(new Event("Authentication", 1, EventType.SYSTEM, EventSeverity.WARNING,
			"User '" + email + "' is not authorized for '" + methodInvoked.getDeclaringClass() + "."
				+ methodInvoked.getName() + "'."));
		requestContext.abortWith(FORBIDDEN);
	    }
	    return;
	} else if (authService.isAuthorizedAdministrator(email, authToken)) {
	    return;
	} else if (methodInvoked.isAnnotationPresent(DenyAll.class)) {
	    eventLogger.logEvent(new Event("Authentication", 1, EventType.SYSTEM, EventSeverity.WARNING,
		    "User '" + email + "' is not authorized for '" + methodInvoked.getDeclaringClass() + "."
			    + methodInvoked.getName() + "'."));
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
	requestContext.abortWith(FORBIDDEN);
    }
}