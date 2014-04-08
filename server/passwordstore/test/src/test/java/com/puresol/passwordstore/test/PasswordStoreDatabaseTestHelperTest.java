package com.puresol.passwordstore.test;

import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.junit.Test;

public class PasswordStoreDatabaseTestHelperTest {

	@Test
	public void testCleanUpScriptPresence() {
		URL cleanupScript = PasswordStoreDatabaseTestHelper.class
				.getResource("/sql/PasswordStoreCleanup.sql");
		assertNotNull(cleanupScript);
	}

}
