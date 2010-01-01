package com.puresol.ua;

import javax.security.auth.Subject;

public interface UA {

	public boolean login(String context);

	public boolean login(String context, String user, String password);

	public boolean logout();

	public boolean isLoggedIn();

	public String getContext();

	public Subject getSubject();

}
