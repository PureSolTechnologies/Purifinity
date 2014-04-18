package com.puresoltechnologies.purifinity.server.passwordstore.client;

/**
 * This principal group contains the authenticated user name for JBoss. See:
 * http
 * ://docs.jboss.org/jbosssecurity/docs/6.0/security_guide/html/Login_Modules
 * .html
 * 
 * @author Rick-Rainer Ludwig
 */
public class CallerPrincipalGroup extends NamedGroup {

	private static final String name = "CallerPrincipal";

	public CallerPrincipalGroup() {
		super(name);
	}
}
