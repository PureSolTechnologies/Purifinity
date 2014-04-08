package com.puresol.passwordstore.test;

import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.junit.Test;

public class PasswordStoreTestTest {

	@Test
	public void testCleanUpScriptPresence() {
		URL cleanupScript = getClass().getResource(
				"/sql/PasswordStoreCleanup.sql");
		assertNotNull(cleanupScript);
	}

}
