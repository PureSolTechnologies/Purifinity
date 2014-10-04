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
    private static final Response ACCESS_UNAUTHORIZED = Response
	    .status(Response.Status.UNAUTHORIZED).entity("Not authorized.")
	    .build();
    // 403 - Forbidden
    private static final Response FORBIDDEN = Response
	    .status(Response.Status.UNAUTHORIZED).entity("Not allowed.")
	    .build();

    @Inject
    private AuthService authService;

    @Context
    private HttpServletRequest request;

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext)
	    throws IOException {
	// Get method invoked.
	Method methodInvoked = resourceInfo.getResourceMethod();
	if (methodInvoked.isAnnotationPresent(PermitAll.class)) {
	    return;
	} else {
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
		requestContext.abortWith(ACCESS_UNAUTHORIZED);
		return;
	    }
	    if (methodInvoked.isAnnotationPresent(RolesAllowed.class)) {

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