package com.puresol.data;

import org.junit.Test;

import com.puresol.testing.Tester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class AddressTest extends TestCase {

    @Test
    public void testGetterAndSetter() {
	Tester.testGetterAndSetter(Address.class);
    }

    @Test
    public void testClone() {
	try {
	    Address address =
		    new Address("Street", "Number", "ZIP", "Town",
			    new Country("de"), "County");
	    Tester.testClone(address);
	} catch (IllegalAddressException e) {
	    e.printStackTrace();
	    Assert.fail("No Exception was expected here!");
	} catch (IllegaleCountryCodeException e) {
	    e.printStackTrace();
	    Assert.fail("No Exception was expected here!");
	}
    }
}
