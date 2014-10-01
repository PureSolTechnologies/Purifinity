package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl.security;

import java.util.Set;

public interface AuthService {

    public boolean isAuthorized(String authId, String authToken,
	    Set<String> rolesAllowed);

}
