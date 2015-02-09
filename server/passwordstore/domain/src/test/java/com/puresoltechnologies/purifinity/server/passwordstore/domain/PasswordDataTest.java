package com.puresoltechnologies.purifinity.server.passwordstore.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PasswordDataTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testNormalCreation() {
	PasswordData passwordData = new PasswordData(1, "abcdef0123456789");
	assertEquals("abcdef0123456789", passwordData.getEncryptedPassword());
	assertEquals(1, passwordData.getMethod());
	assertEquals("1:abcdef0123456789", passwordData.toString());
    }

    @Test
    public void testInvalidMethodZero() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage("Method must not be zero or negative.");
	new PasswordData(0, "abcdef0123456789");
    }

    @Test
    public void testInvalidMethodNegative() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage("Method must not be zero or negative.");
	new PasswordData(-1, "abcdef0123456789");
    }

    @Test
    public void testInvalidMethodNullEncryptedPassword() {
	expectedException.expect(NullPointerException.class);
	expectedException
		.expectMessage("The encrypted password must not be null.");
	new PasswordData(1, null);
    }

    @Test
    public void testInvalidMethodEmptyEncryptedPassword() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException
		.expectMessage("The encrypted password must not be empty.");
	new PasswordData(1, "");
    }

    @Test
    public void testFromString() {
	PasswordData passwordData = PasswordData
		.fromString("12:1234567890abcdef");
	assertEquals("1234567890abcdef", passwordData.getEncryptedPassword());
	assertEquals(12, passwordData.getMethod());
	assertEquals("12:1234567890abcdef", passwordData.toString());
    }

    @Test
    public void testFromStringInvalidMethodZero() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage("Method must not be zero or negative.");
	PasswordData.fromString("0:1234567890abcdef");
    }

    @Test
    public void testFromStringInvalidMethodNegative() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage("Method must not be zero or negative.");
	PasswordData.fromString("0:1234567890abcdef");
    }

    @Test
    public void testFromStringInvalidEncryptedPasswordEmpty() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException
		.expectMessage("Invalid format of password data (no encrypted password defined).");
	PasswordData.fromString("12:");
    }

    @Test
    public void testFromStringInvalidMethodEmpty() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException
		.expectMessage("Invalid format of password data (no method defined).");
	PasswordData.fromString(":abcdef1234567890");
    }

    @Test
    public void testFromStringInvalidFormatNoColon() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException
		.expectMessage("Invalid format of password data (no colon found).");
	PasswordData.fromString("abcdef0123456789");
    }

    @Test
    public void testFromStringInvalidFormatMultipleColons() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException
		.expectMessage("Invalid format of password data (multiple colons found).");
	PasswordData.fromString("12:abcdef0123456789:14");
    }

}
