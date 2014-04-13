package com.puresoltechnologies.purifinity.server.wildfly.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.wildfly.utils.EmailAddressValidator;

public class EmailAddressValidatorTest {

	@Test
	public void testValidationForValidAddresses() {
		assertTrue(EmailAddressValidator.validate("a@a.com"));
		assertTrue(EmailAddressValidator.validate("rick-rainer.ludwig@web.de"));
	}

	@Test
	public void testValidationForInvalidAddresses() {
		assertFalse(EmailAddressValidator.validate(null));
		assertFalse(EmailAddressValidator.validate(""));
		assertFalse(EmailAddressValidator.validate("@a.com"));
		assertFalse(EmailAddressValidator.validate("a@"));
		assertFalse(EmailAddressValidator.validate("a@a."));
	}
}