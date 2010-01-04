package com.puresol.data;

import org.junit.Test;

import com.puresol.testing.Tester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CompanyNameTest extends TestCase {

    @Test
    public void testConstructor() {
	try {
	    CompanyName name = new CompanyName("name", "form");
	    Assert.assertEquals("name form", name.toString());
	} catch (IllegalCompanyNameException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected here!");
	}
    }

    @Test
    public void testException() {
	try {
	    new CompanyName(null, "form");
	    Assert.fail("Exception was expected!");
	} catch (IllegalCompanyNameException e) {
	    // nothing to catch here, exception was expected
	}
	try {
	    new CompanyName("", "form");
	    Assert.fail("Exception was expected!");
	} catch (IllegalCompanyNameException e) {
	    // nothing to catch here, exception was expected
	}
	try {
	    new CompanyName("name", null);
	    Assert.fail("Exception was expected!");
	} catch (IllegalCompanyNameException e) {
	    // nothing to catch here, exception was expected
	}
    }

    @Test
    public void testClone() {
	try {
	    CompanyName name = new CompanyName("name", "form");
	    Tester.testClone(name);
	} catch (IllegalCompanyNameException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected here!");
	}
    }
}
