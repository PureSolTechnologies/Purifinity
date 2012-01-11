package com.puresol.ua;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.junit.Test;

public class DefaultCallbackHandlerTest {

	@Test
	public void testHandle() {
		try {
			DefaultCallbackHandler handler = new DefaultCallbackHandler(
					"user's name", "21 is only half the truth");
			assertNotNull(handler);
			Callback[] callbacks = new Callback[2];
			callbacks[0] = new NameCallback("user");
			callbacks[1] = new PasswordCallback("password", false);
			handler.handle(callbacks);
			assertEquals(NameCallback.class, callbacks[0].getClass());
			assertEquals(PasswordCallback.class, callbacks[1].getClass());
			NameCallback nameCallback = (NameCallback) callbacks[0];
			PasswordCallback passwordCallback = (PasswordCallback) callbacks[1];
			assertEquals("user's name", nameCallback.getName());
			assertEquals("21 is only half the truth",
					String.valueOf(passwordCallback.getPassword()));
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (UnsupportedCallbackException e) {
			e.printStackTrace();
			fail();
		}
	}
}
