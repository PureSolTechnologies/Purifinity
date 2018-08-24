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
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HttpMethod;
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
import com.puresoltechnologies.purifinity.server.core.api.PurifinityConfiguration;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesStore;
import com.puresoltechnologies.purifinity.server.core.api.preferences.SystemPreferenceChange;
import com.puresoltechnologies.purifinity.server.core.api.preferences.SystemPreferenceChangeEvent;
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
 * <ul>
 * <li>http://bitsuppliers.com/securing-rest-resources-with-java-ee-7-and-jax
 * -rs -2-0</li>
 * <li>http://www.developerscrappad.com/1814/java/java-ee/rest-jax-rs/java-ee
 * -7- jax
 * -rs-2-0-simple-rest-api-authentication-authorization-with-custom-http- header
 * /#sthash.ZvuVwQ2p.eaof</li>
 * <li>http://www.aschua.de/blog/pairing-angularjs-and-javaee-for-
 * authentication</li>
 * <li>http://stackoverflow.com/questions/3297048/403-forbidden-vs-401-
 * unauthorized-http-responses</li>
 * </ul>
 * 
 * @author Rick-Rainer Ludwig
 *
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationAndAuthorizationFilter implements ContainerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationAndAuthorizationFilter.class);

    private static Event createUserNotAuthenticatedEvent() {
	return new Event("Authentication", 1, EventType.SYSTEM, EventSeverity.WARNING,
		"User was not authenticated, yet.");
    }

    private static Event createIllegalEmailAddressEvent(String authIdString) {
	return new Event("Authentication", 2, EventType.SYSTEM, EventSeverity.WARNING,
		"Invalid email address '" + authIdString + "' provided.");
    }

    private static Event createIllegalTokenEvent(String authTokenString, EmailAddress email) {
	return new Event("Authentication", 3, EventType.SYSTEM, EventSeverity.WARNING,
		"Invalid user token '" + authTokenString + "' for user '" + email + "'.");
    }

    private static Event createInvalidAuthenticationDataEvent(String authTokenString, EmailAddress email) {
	return new Event("Authentication", 4, EventType.SYSTEM, EventSeverity.WARNING,
		"Invalid user token '" + authTokenString + "' for user '" + email + "'.");
    }

    private static Event createInsufficientPrivilegesEvent(Method methodInvoked, EmailAddress email) {
	return new Event("Authentication", 5, EventType.SYSTEM, EventSeverity.WARNING,
		"User '" + email + "' is not authorized for '" + methodInvoked.getDeclaringClass() + "."
			+ methodInvoked.getName() + "'.");
    }

    private static Event createForbiddenEvent(Method methodInvoked, EmailAddress email) {
	return new Event("Authentication", 6, EventType.SYSTEM, EventSeverity.WARNING,
		"Forbidden feature. User '" + email + "' is not authorized for '" + methodInvoked.getDeclaringClass()
			+ "." + methodInvoked.getName() + "'.");
    }

    private static Event createUnspecifiedAuthorizationEvent(Method methodInvoked, EmailAddress email) {
	return new Event("Authentication", 7, EventType.SYSTEM, EventSeverity.WARNING,
		"User '" + email + "' is not authorized for '" + methodInvoked.getDeclaringClass()
			+ ", because there is no authorization specfied on." + methodInvoked.getName() + "'.");
    }

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

    @Inject
    private PreferencesStore preferencesStore;

    private boolean anonymousCanRead = PurifinityConfiguration.ANONYMOUS_CAN_READ.getDefaultValue();

    @PostConstruct
    public void postConstruct() {
	logger.info("REST security is enabled.");
	anonymousCanRead = preferencesStore.getSystemPreference(PurifinityConfiguration.ANONYMOUS_CAN_READ).getValue();
    }

    @PreDestroy
    public void preDestroy() {
	logger.info("REST security is disabled.");
    }

    public void onSystemPreferenceChange(@Observes @SystemPreferenceChange SystemPreferenceChangeEvent event) {
	if (event.getConfigurationParameter().equals(PurifinityConfiguration.ANONYMOUS_CAN_READ)) {
	    anonymousCanRead = (Boolean) event.getValue();
	    logger.info("AnonymousCanRead was reconfigured to '" + anonymousCanRead + "'.");
	}
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
	/* Get AuthId and AuthToken from HTTP-Header. */
	String authIdString = requestContext.getHeaderString(AuthElement.AUTH_ID_HEADER);
	String authTokenString = requestContext.getHeaderString(AuthElement.AUTH_TOKEN_HEADER);
	if ((authIdString == null) || (authIdString.isEmpty()) || (authTokenString == null)
		|| (authTokenString.isEmpty())) {
	    if (!isOpenGlobally(requestContext)) {
		/*
		 * Method is not globally open and we do not have authentication
		 * data, so we need to ask for it.
		 */
		eventLogger.logEvent(createUserNotAuthenticatedEvent());
		requestContext.abortWith(ACCESS_UNAUTHORIZED);
	    }
	    return;
	}
	/* Check email address format and convert it... */
	EmailAddress email;
	try {
	    email = new EmailAddress(authIdString);
	} catch (IllegalArgumentException e) {
	    if (!isOpenGlobally(requestContext)) {
		eventLogger.logEvent(createIllegalEmailAddressEvent(authIdString));
		requestContext.abortWith(ACCESS_UNAUTHORIZED);
	    }
	    return;
	}
	/* Check authentication token and convert it... */
	UUID authToken;
	try {
	    authToken = UUID.fromString(authTokenString);
	} catch (IllegalArgumentException e) {
	    if (!isOpenGlobally(requestContext)) {
		eventLogger.logEvent(createIllegalTokenEvent(authTokenString, email));
		requestContext.abortWith(ACCESS_UNAUTHORIZED);
	    }
	    return;
	}
	/* Check for correct authentication data... */
	if (authService.isAuthenticated(email, authToken)) {
	    authService.updateActivity(email, authToken);
	} else {
	    if (!isOpenGlobally(requestContext)) {
		/*
		 * Authentication data does not fit, so we do not need to check
		 * further...
		 */
		eventLogger.logEvent(createInvalidAuthenticationDataEvent(authTokenString, email));
		requestContext.abortWith(ACCESS_UNAUTHORIZED);
	    }
	    return;
	}
	/* Get method invoked. */
	Method methodInvoked = resourceInfo.getResourceMethod();
	/*
	 * Check for certain roles to be allowed...
	 */
	if (isOpenGlobally(requestContext)) {
	    return;
	} else if (authService.isAuthorizedAdministrator(email, authToken)) {
	    /* User is an administrator, so she is allowed. */
	    return;
	} else if (methodInvoked.isAnnotationPresent(RolesAllowed.class)) {
	    RolesAllowed rolesAllowedAnnotation = methodInvoked.getAnnotation(RolesAllowed.class);
	    Set<String> rolesAllowed = new HashSet<>(Arrays.asList(rolesAllowedAnnotation.roles()));
	    if (!authService.isAuthorized(email, authToken, rolesAllowed)) {
		eventLogger.logEvent(createInsufficientPrivilegesEvent(methodInvoked, email));
		requestContext.abortWith(FORBIDDEN);
	    }
	    return;
	} else if (methodInvoked.isAnnotationPresent(DenyAll.class)) {
	    /* Globally forbidden method. */
	    eventLogger.logEvent(createForbiddenEvent(methodInvoked, email));
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
	eventLogger.logEvent(createUnspecifiedAuthorizationEvent(methodInvoked, email));
	requestContext.abortWith(FORBIDDEN);
    }

    private boolean isOpenGlobally(ContainerRequestContext requestContext) {
	/* Get method invoked. */
	Method methodInvoked = resourceInfo.getResourceMethod();
	/* Check if open method... */
	if (methodInvoked.isAnnotationPresent(PermitAll.class)) {
	    /*
	     * Method is globally permitted, so we proceed without interactions.
	     */
	    return true;
	}
	/* Check whether anonymous is allowed to read... */
	if ((anonymousCanRead) && (HttpMethod.GET.equals(requestContext.getMethod()))) {
	    /*
	     * Anonymous is allowed read, so we can open GET methods globally
	     * and skip all other tests for performance reasons.
	     */
	    return true;
	}
	return false;
    }
}