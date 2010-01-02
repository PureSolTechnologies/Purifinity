package com.puresol.ua.ldap;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import com.puresol.ua.ExtPrincipal;
import com.puresol.ua.SubjectInformation;

/**
 * This login module is designed for LDAP authentication against a LDAP server
 * with PureSolTechnologies schema.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LdapLoginModule implements LoginModule {

	private static final Logger logger = Logger
			.getLogger(LdapLoginModule.class);

	private Subject subject = null;
	private CallbackHandler callbackHandler = null;
	@SuppressWarnings("unused")
	private Map<String, ?> sharedState = null;
	private Map<String, ?> options;
	@SuppressWarnings("unused")
	private boolean debug = false;
	private ExtPrincipal principal = null;
	private DirContext ctx = null;
	private SubjectInformation subjectInformation = null;

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		this.options = options;
		debug = "true".equalsIgnoreCase((String) options.get("debug"));
	}

	@Override
	public boolean login() throws LoginException {
		try {
			NameCallback nameCallback = new NameCallback("name");
			PasswordCallback passwordCallback = new PasswordCallback(
					"password", false);

			Callback[] callbacks = new Callback[3];
			callbacks[0] = new TextOutputCallback(
					TextOutputCallback.INFORMATION,
					"Please put a user name and password in!");
			callbacks[1] = nameCallback;
			callbacks[2] = passwordCallback;
			callbackHandler.handle(callbacks);

			Hashtable<String, String> env = new Hashtable<String, String>();

			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, "ldap://" + options.get("host") + ":"
					+ options.get("port"));
			env.put(Context.SECURITY_PRINCIPAL, "uid=" + nameCallback.getName()
					+ ",ou=people,o=PureSolTechnologies");
			env.put(Context.SECURITY_CREDENTIALS, String
					.valueOf(passwordCallback.getPassword()));
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.REFERRAL, "ignore");
			env.put(Context.SECURITY_PROTOCOL, "simple");

			// TODO put SSL encryption in here...

			ctx = new InitialDirContext(env);

			subjectInformation = new SubjectInformation();
			Attributes attribs = ctx.getAttributes("uid="
					+ nameCallback.getName()
					+ ",ou=people,o=PureSolTechnologies");
			NamingEnumeration<?> enumAttrib = attribs.getAll();
			while (enumAttrib.hasMore()) {
				// TODO put here some meaningful principals in for the different
				// sections...
				Attribute attrib = (Attribute) enumAttrib.nextElement();
				if (attrib.getID().equals("photo")) {
					Object photo = attrib.get();
					byte[] buf = (byte[]) photo;
					subjectInformation.setPicture(new ImageIcon(buf));
				} else if (attrib.getID().equals("sn")) {
					subjectInformation.setSurname((String) attrib.get());
				}
				System.out.println(attrib.getID());
				System.out.println(attrib.getClass().getName());
			}

			ctx.close();
		} catch (NamingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (UnsupportedCallbackException e) {
			logger.error(e.getMessage(), e);
		}
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		if (subjectInformation != null) {
			subject.getPrincipals().add(subjectInformation);
		}
		principal = new ExtPrincipal();
		subject.getPrincipals().add(principal);
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		principal = null;
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(principal);
		principal = null;
		return true;
	}
}
