package com.puresol.data;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.testing.Tester;

public class CompanyNameTest {

	@Test
	public void testConstructor() {
		try {
			CompanyName name = new CompanyName("name", "form");
			assertEquals("name form", name.toString());
		} catch (IllegalCompanyNameException e) {
			e.printStackTrace();
			fail("No exception was expected here!");
		}
	}

	@Test
	public void testException() {
		try {
			new CompanyName(null, "form");
			fail("Exception was expected!");
		} catch (IllegalCompanyNameException e) {
			// nothing to catch here, exception was expected
		}
		try {
			new CompanyName("", "form");
			fail("Exception was expected!");
		} catch (IllegalCompanyNameException e) {
			// nothing to catch here, exception was expected
		}
		try {
			new CompanyName("name", null);
			fail("Exception was expected!");
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
			fail("No exception was expected here!");
		}
	}
}
