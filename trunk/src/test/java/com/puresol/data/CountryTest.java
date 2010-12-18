package com.puresol.data;

import org.junit.Test;

import com.puresol.testing.Tester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CountryTest extends TestCase {

    @Test
    public void testConstructor() {
	try {
	    Country country = new Country("de");
	    Assert.assertEquals("DE", country.getISOCountry());
	    Country country2 = new Country(country);
	    Assert.assertEquals("DE", country2.getISOCountry());
	} catch (IllegaleCountryCodeException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected here!");
	}
    }

    @Test
    public void testClone() {
	try {
	    Country country = new Country("de");
	    Tester.testClone(country);
	} catch (IllegaleCountryCodeException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected here!");
	}
    }

    @Test
    public void testException() {
	try {
	    new Country("IV");
	} catch (IllegaleCountryCodeException e) {
	    // nothing to catch, exception was expected...
	}
	try {
	    new Country((Country) null);
	} catch (IllegaleCountryCodeException e) {
	    // nothing to catch, exception was expected...
	}
    }
}
