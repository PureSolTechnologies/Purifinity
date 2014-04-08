package com.puresol.passwordstore.core.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresol.passwordstore.core.api.PasswordData;

public class PasswordDataTest {

	@Test
	public void testToString() {
		PasswordData data = new PasswordData(2, "encryptedPassword");
		assertEquals("2:encryptedPassword", data.toString());
	}

	@Test
	public void testFromString() {
		PasswordData data = PasswordData.fromString("2:encryptedPassword");
		assertEquals(2, data.getMethod());
		assertEquals("encryptedPassword", data.getEncryptedPassword());
	}
}
