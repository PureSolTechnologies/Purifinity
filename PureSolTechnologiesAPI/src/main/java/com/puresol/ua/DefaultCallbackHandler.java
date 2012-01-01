package com.puresol.ua;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 * This is the default callback handler for fixed user and password settings
 * from source code or configuration files. The callback handler is initialized
 * with both and just returns the values as soon as its needed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DefaultCallbackHandler implements CallbackHandler {

	private String user = "";
	private String password = "";

	public DefaultCallbackHandler(String user, String password) {
		this.user = user;
		this.password = password;
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		for (Callback callback : callbacks) {
			if (callback instanceof NameCallback) {
				NameCallback nc = (NameCallback) callback;
				nc.setName(user);
			} else if (callback instanceof PasswordCallback) {
				PasswordCallback pc = (PasswordCallback) callback;
				pc.setPassword(password.toCharArray());
			}
		}
	}

}
