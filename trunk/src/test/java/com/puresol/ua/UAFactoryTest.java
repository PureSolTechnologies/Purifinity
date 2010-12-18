package com.puresol.ua;

import javax.swingx.PasswordDialog;
import javax.swingx.config.ClassRegistry;
import javax.swingx.config.ClassRegistryElement;
import javax.swingx.config.ClassRegistryElementType;

import org.junit.Test;

import com.puresol.gui.ua.SubjectInformationDialog;

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
		UA ua = UAFactory.create(getClass());
		Assert.assertNotNull(ua);
		Assert.assertEquals("com.puresol.ua.TestJAAS", ua.getClass().getName());
	}

	@Test
	public void testLogin() {
		ClassRegistry.register(UA.class, new ClassRegistryElement(
				ClassRegistryElementType.FACTORY, "com.puresol.ua.TestJAAS"));
		UA ua = UAFactory.create(getClass());
		Assert.assertTrue(ua.login("PureSolTechnologies"));
	}

	/**
	 * The main function was included for manual testing of a login with
	 * PasswordDialogLoginModule. Just run this test class as Application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		UA ua = UAFactory.create(UAFactoryTest.class);
		PasswordDialog passwordDialog = new PasswordDialog();
		if (passwordDialog.run()) {
			if (ua.login("PureSolTechnologies", passwordDialog.getUsername(),
					passwordDialog.getPassword())) {
				System.out.println("Success!!!");
			}
			ua.getInformation().print();
			new SubjectInformationDialog(ua.getInformation()).run();
		}
	}
}
