package com.puresol.ua;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

public class DefaultCallbackHandlerTest extends TestCase {

	@Test
	public void testHandle() {
		try {
			DefaultCallbackHandler handler = new DefaultCallbackHandler(
					"user's name", "21 is only half the truth");
			Assert.assertNotNull(handler);
			Callback[] callbacks = new Callback[2];
			callbacks[0] = new NameCallback("user");
			callbacks[1] = new PasswordCallback("password", false);
			handler.handle(callbacks);
			Assert.assertEquals(NameCallback.class, callbacks[0].getClass());
			Assert
					.assertEquals(PasswordCallback.class, callbacks[1]
							.getClass());
			NameCallback nameCallback = (NameCallback) callbacks[0];
			PasswordCallback passwordCallback = (PasswordCallback) callbacks[1];
			Assert.assertEquals("user's name", nameCallback.getName());
			Assert.assertEquals("21 is only half the truth", String
					.valueOf(passwordCallback.getPassword()));
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (UnsupportedCallbackException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
