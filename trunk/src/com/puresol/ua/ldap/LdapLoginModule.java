package com.puresol.ua.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import com.puresol.ua.SubjectInformation;

/**
 * This login module is designed for LDAP authentication against a LDAP server
 * with PureSolTechnologies schema.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LdapLoginModule extends AbstractLdapLoginModule {

	private static final Logger logger = Logger
			.getLogger(LdapLoginModule.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Hashtable<String, String> createLDAPEnvironmentProperties(
			Hashtable<Class<?>, Callback> callbacks) {
		// TODO put SSL encryption in here...
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://" + getHost() + ":" + getPort());
		env.put(Context.SECURITY_PRINCIPAL, "uid="
				+ ((NameCallback) callbacks.get(NameCallback.class)).getName()
				+ ",ou=people,o=PureSolTechnologies");
		env.put(Context.SECURITY_CREDENTIALS, String
				.valueOf(((PasswordCallback) callbacks
						.get(PasswordCallback.class)).getPassword()));
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.REFERRAL, "ignore");
		env.put(Context.SECURITY_PROTOCOL, "simple");
		return env;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void updateSubjectInformation(
			SubjectInformation subjectInformation, DirContext ctx, String userID)
			throws LoginException {
		try {
			Hashtable<String, Object> attributes;
			attributes = attributes2Hashtable(ctx.getAttributes("uid=" + userID
					+ ",ou=people,o=PureSolTechnologies"));
			if (attributes.containsKey("photo")) {
				Object photo = attributes.get("photo");
				byte[] buf = (byte[]) photo;
				subjectInformation.setPicture(new ImageIcon(buf));
			}
			if (attributes.containsKey("sn")) {
				subjectInformation.setSurname((String) attributes.get("sn"));
			}
			if (attributes.containsKey("givenName")) {
				subjectInformation.setGivenName((String) attributes
						.get("givenName"));
			}
			if (attributes.containsKey("room")) {
				subjectInformation
						.setGivenName((String) attributes.get("room"));
			}
		} catch (NamingException e) {
			logger.error(e.getMessage(), e);
			throw new LoginException(e.getMessage());
		}
	}
}
