package com.puresol.ua.test;

import static org.junit.Assert.*;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.junit.Test;

/**
 * This is a LoginModule for testing purposes.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TestLoginModule implements LoginModule {

	@Override
	public boolean abort() throws LoginException {
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		return true;
	}

	@Override
	public void initialize(Subject arg0, CallbackHandler arg1,
			Map<String, ?> arg2, Map<String, ?> arg3) {
	}

	@Override
	public boolean login() throws LoginException {
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		return true;
	}

	@Test
	public void testInstance() {
		assertNotNull(new TestLoginModule());
	}
}
