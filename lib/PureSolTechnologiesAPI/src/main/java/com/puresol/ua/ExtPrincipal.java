package com.puresol.ua;

import java.security.Principal;

public class ExtPrincipal implements Principal {

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

	@Override
	public String toString() {
		return getName();
	}
	
}
