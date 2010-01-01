package com.puresol.ua;

import javax.swingx.config.ClassRegistry;
import javax.swingx.config.ClassRegistryElement;
import javax.swingx.config.ClassRegistryElementType;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * This test checks the basic functionality of UAFactory and UA.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class UAFactoryTest extends TestCase {

	@Test
	public void testCreate() {
		ClassRegistry.register(UA.class, new ClassRegistryElement(
				ClassRegistryElementType.FACTORY, "com.puresol.ua.TestJAAS"));
		UA ua = UAFactory.create();
		Assert.assertNotNull(ua);
		Assert.assertEquals("com.puresol.ua.TestJAAS", ua.getClass().getName());
	}

	@Test
	public void testLogin() {
		ClassRegistry.register(UA.class, new ClassRegistryElement(
				ClassRegistryElementType.FACTORY, "com.puresol.ua.TestJAAS"));
		UA ua = UAFactory.create();
		Assert.assertTrue(ua.login("PureSolTechnologies"));
	}

	/**
	 * The main function was included for manual testing of a login with
	 * PasswordDialogLoginModule. Just run this test class as Application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		UA ua = UAFactory.create();
		if (ua.login("PureSolTechnologies")) {
			System.out.println("Success!!!");
		}
	}
}
