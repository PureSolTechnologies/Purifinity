package com.puresol.gui;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApplicationTest {

	@Test
	public void testGetCopyrightMessage() {
		String string = Application.getCopyrightMessage();
		assertNotNull(string);
		assertFalse(string.isEmpty());
	}

	@Test
	public void testGetVendorInformation() {
		String string = Application.getVendorInformation();
		assertNotNull(string);
		assertFalse(string.isEmpty());
	}

	@Test
	public void testGetContactInformation() {
		String string = Application.getContactInformation();
		assertNotNull(string);
		assertFalse(string.isEmpty());
	}
}
