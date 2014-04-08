package com.puresol.passwordstore.client;

/**
 * This principal contains principals with names of the roles the holding
 * subject is member in. See:
 * http://docs.jboss.org/jbosssecurity/docs/6.0/security_guide
 * /html/Login_Modules.html
 * 
 * @author Rick-Rainer Ludwig
 */
public class RolesGroup extends NamedGroup {

	private static final String name = "Roles";

	public RolesGroup() {
		super(name);
	}
}
