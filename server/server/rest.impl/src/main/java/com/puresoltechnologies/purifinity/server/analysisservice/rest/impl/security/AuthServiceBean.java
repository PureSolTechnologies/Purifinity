package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl.security;

import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuthServiceBean implements AuthService {

    @Inject
    private Authenticator authenticator;

    @Override
    public boolean isAuthorized(String username, String authToken,
	    Set<String> rolesAllowed) {
	User user = authenticator.findByUsernameAndAuthToken(username,
		authToken);
	if (user != null) {
	    return rolesAllowed.contains(user.getAuthRole());
	} else {
	    return false;
	}
    }
}