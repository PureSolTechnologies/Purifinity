package com.puresol.ua;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.config.ClassRegistry;
import com.puresol.config.ClassRegistryElement;
import com.puresol.config.ClassRegistryElementType;
import com.puresol.gui.DialogButtons;
import com.puresol.gui.LoginDialog;
import com.puresol.gui.ua.SubjectInformationDialog;

/**
 * This test checks the basic functionality of UAFactory and UA.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class UAFactoryTest {

    @Test
    public void testCreate() {
	ClassRegistry.register(UA.class, new ClassRegistryElement(
		ClassRegistryElementType.FACTORY, "com.puresol.ua.TestJAAS"));
	UA ua = UAFactory.create(getClass());
	assertNotNull(ua);
	assertEquals("com.puresol.ua.TestJAAS", ua.getClass().getName());
    }

    @Test
    public void testLogin() {
	ClassRegistry.register(UA.class, new ClassRegistryElement(
		ClassRegistryElementType.FACTORY, "com.puresol.ua.TestJAAS"));
	UA ua = UAFactory.create(getClass());
	assertTrue(ua.login("PureSolTechnologies"));
    }

    /**
     * The main function was included for manual testing of a login with
     * PasswordDialogLoginModule. Just run this test class as Application.
     * 
     * @param args
     */
    public static void main(String[] args) {
	UA ua = UAFactory.create(UAFactoryTest.class);
	LoginDialog passwordDialog = new LoginDialog();
	passwordDialog.setVisible(true);
	if (passwordDialog.getClosingButton() == DialogButtons.OK) {
	    if (ua.login("PureSolTechnologies", passwordDialog.getUsername(),
		    passwordDialog.getPassword())) {
		System.out.println("Success!!!");
	    }
	    ua.getInformation().print();
	    new SubjectInformationDialog(ua.getInformation()).setVisible(true);
	}
    }
}
