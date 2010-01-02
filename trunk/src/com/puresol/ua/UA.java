package com.puresol.ua;

import java.security.Principal;
import java.util.Set;

import javax.security.auth.Subject;

/**
 * This is the UA interface for all UAs to be implemented within this
 * PureSolTechnologies API. Only a mininum of functionality is to be used
 * outside the UA itself.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface UA {

	public boolean login(String context);

	public boolean login(String context, String user, String password);

	public boolean logout();

	public boolean isLoggedIn();

	public String getContext();

	public Subject getSubject();

	public Set<Principal> getPrincipals();

	public Set<Principal> getPrincipals(Class<? extends Principal> principal);
	
	public SubjectInformation getInformation();
}
