package com.puresol.data;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.testing.Tester;

public class EMailAddressTest extends TestCase {

    @Test
    public void testConstructor() {
	try {
	    EMailAddress email = new EMailAddress("name@server.domain");
	    Assert.assertEquals("name@server.domain", email.toString());
	    email = new EMailAddress("name", "server.domain");
	    Assert.assertEquals("name@server.domain", email.toString());
	} catch (MailformedEMailException e) {
	    e.printStackTrace();
	    Assert.fail("No excpetion was expected here!");
	}
    }

    @Test
    public void testException() {
	try {
	    new EMailAddress("server.domain");
	} catch (MailformedEMailException e) {
	    // nothing to catch, exception was expected
	}
	try {
	    new EMailAddress("name@server");
	} catch (MailformedEMailException e) {
	    // nothing to catch, exception was expected
	}
    }

    @Test
    public void testClone() {
	try {
	    EMailAddress email = new EMailAddress("name@server.domain");
	    Tester.testClone(email);
	} catch (MailformedEMailException e) {
	    e.printStackTrace();
	    Assert.fail("No excpetion was expected here!");
	}
    }
}
