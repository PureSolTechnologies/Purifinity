package com.puresol.ua.ldap;

import java.io.IOException;
import java.security.Principal;
import java.util.Hashtable;
import java.util.Map;

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

import org.apache.log4j.Logger;

import com.puresol.ua.ExtPrincipal;
import com.puresol.ua.SubjectInformation;

/**
 * This login module is designed for LDAP authentication against a LDAP server.
 * It's an abstract class for inheritance to a meaningful class with valid
 * connection information and content handling.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
abstract public class AbstractLdapLoginModule implements LoginModule {

	private static final Logger logger = Logger
			.getLogger(AbstractLdapLoginModule.class);

	private Subject subject = null;
	private CallbackHandler callbackHandler = null;
	@SuppressWarnings("unused")
	private Map<String, ?> sharedState = null;
	private Map<String, ?> options;
	@SuppressWarnings("unused")
	private boolean debug = false;
	private SubjectInformation subjectInformation = null;
	private DirContext ctx = null;

	protected String getHost() {
		return (String) options.get("host");
	}

	protected String getPort() {
		return (String) options.get("port");
	}

	@Override
	public final void initialize(Subject subject,
			CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		this.options = options;
		debug = "true".equalsIgnoreCase((String) options.get("debug"));
		subjectInformation = getCurrentSubjectInformation();
		// ctx is initialized and reset in login
	}

	@Override
	public final boolean login() throws LoginException {
		try {
			Hashtable<Class<?>, Callback> callbacks = createCallbacks();
			callbackHandler.handle(callbacks2Array(callbacks));
			Hashtable<String, String> env = createLDAPEnvironmentProperties(callbacks);
			ctx = new InitialDirContext(env);
			updateSubjectInformation(subjectInformation, ctx,
					((NameCallback) callbacks.get(NameCallback.class))
							.getName());
			ctx.close();
			ctx = null;
			return true;
		} catch (NamingException e) {
			logger.error(e.getMessage(), e);
			throw new LoginException(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new LoginException(e.getMessage());
		} catch (UnsupportedCallbackException e) {
			logger.error(e.getMessage(), e);
			throw new LoginException(e.getMessage());
		}
	}

	private Hashtable<Class<?>, Callback> createCallbacks() {
		Hashtable<Class<?>, Callback> callbacks = new Hashtable<Class<?>, Callback>();
		callbacks.put(TextOutputCallback.class, new TextOutputCallback(
				TextOutputCallback.INFORMATION,
				"Please put a user name and password in!"));
		callbacks.put(NameCallback.class, new NameCallback("name"));
		callbacks.put(PasswordCallback.class, new PasswordCallback("password",
				false));
		return callbacks;
	}

	private Callback[] callbacks2Array(Hashtable<Class<?>, Callback> callbacks) {
		Callback[] callbackArray = new Callback[callbacks.values().size()];
		int index = 0;
		for (Callback callback : callbacks.values()) {
			callbackArray[index] = callback;
			index++;
		}
		return callbackArray;
	}

	/**
	 * This method sets all properties needed to connect to the LDAP server into
	 * a hashtable,
	 * 
	 * @param callbacks
	 *            are the current callbacks holding all information about the
	 *            login.
	 * @return A hashtable with properties is returned.
	 */
	abstract protected Hashtable<String, String> createLDAPEnvironmentProperties(
			Hashtable<Class<?>, Callback> callbacks);

	/**
	 * This method overrides/ updates the specified subjectInformation
	 * parameter. The current DirContext and the userID are available for
	 * further information.
	 * 
	 * @param subjectInformation
	 *            is a reference to the object to be updated.
	 * @param ctx
	 *            is the current DirContext.
	 * @param userID
	 *            is the current user ID.
	 * @throws LoginException
	 *             is thrown in case of an failure.
	 */
	abstract protected void updateSubjectInformation(
			SubjectInformation subjectInformation, DirContext ctx, String userID)
			throws LoginException;

	/**
	 * This method checks the current principals for a SubjectInformation object
	 * and returns it if present. If there is none found, a new instance of
	 * SubjectInformation is returned.
	 * 
	 * @return A already present or a new instance of SubjectInformation is
	 *         returned.
	 */
	private SubjectInformation getCurrentSubjectInformation() {
		for (Principal principal : subject.getPrincipals()) {
			if (principal instanceof SubjectInformation) {
				return (SubjectInformation) principal;
			}
		}
		return new SubjectInformation();
	}

	protected static Hashtable<String, Object> attributes2Hashtable(
			Attributes attributes) {
		Hashtable<String, Object> hashtable = new Hashtable<String, Object>();
		try {
			NamingEnumeration<? extends Attribute> attributesEnumeration = attributes
					.getAll();
			while (attributesEnumeration.hasMore()) {
				Attribute attribute = (Attribute) attributesEnumeration.next();
				hashtable.put(attribute.getID(), attribute.get());
			}
		} catch (NamingException e) {
			logger.error(e.getMessage(), e);
		}
		return hashtable;
	}

	@Override
	public boolean commit() throws LoginException {
		if (subjectInformation != null) {
			if (!subject.getPrincipals().contains(subjectInformation)) {
				subject.getPrincipals().add(subjectInformation);
			}
		}
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		subjectInformation = null;
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		if (!subject.getPrincipals().contains(subjectInformation)) {
			subject.getPrincipals().remove(subjectInformation);
		}
		subjectInformation = null;
		return true;
	}
}
