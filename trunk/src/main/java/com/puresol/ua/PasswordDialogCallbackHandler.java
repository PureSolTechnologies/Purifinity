package com.puresol.ua;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.swingx.PasswordDialog;

/**
 * This is a special CallbackHandler for Login without username and password. In
 * this case this callback handler is used and a PasswordDialog shows up within
 * the current Application.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PasswordDialogCallbackHandler implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		PasswordDialog passwd = new PasswordDialog();
		for (Callback callback : callbacks) {
			if (callback instanceof TextOutputCallback) {
				TextOutputCallback toc = (TextOutputCallback) callback;
				passwd.setMessage(toc.getMessage());
			} else if (callback instanceof NameCallback) {
				NameCallback nc = (NameCallback) callback;
				passwd.setUsername(nc.getName());
			}
		}
		if (passwd.run()) {
			for (Callback callback : callbacks) {
				if (callback instanceof NameCallback) {
					NameCallback nc = (NameCallback) callback;
					nc.setName(passwd.getUsername());
				} else if (callback instanceof PasswordCallback) {
					PasswordCallback pc = (PasswordCallback) callback;
					pc.setPassword(passwd.getPassword().toCharArray());
				}
			}
		}
	}

}
