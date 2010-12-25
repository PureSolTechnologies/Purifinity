package com.puresol.data;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.testing.Tester;

public class AddressTest {

	@Test
	public void testGetterAndSetter() {
		Tester.testGetterAndSetter(Address.class);
	}

	@Test
	public void testClone() {
		try {
			Address address = new Address("Street", "Number", "ZIP", "Town",
					new Country("de"), "County");
			Tester.testClone(address);
		} catch (IllegalAddressException e) {
			e.printStackTrace();
			fail("No Exception was expected here!");
		} catch (IllegaleCountryCodeException e) {
			e.printStackTrace();
			fail("No Exception was expected here!");
		}
	}
}
