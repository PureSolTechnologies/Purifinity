package com.puresol.license.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import com.puresol.license.impl.crypt.SecurityUtilities;

public class SecurityUtilitiesTest {

    @Test
    public void testGetServiceTypes() {
	System.out.println("=================================");
	System.out.println("Available Security Service Types:");
	System.out.println("=================================");
	Set<String> serviceTypes = SecurityUtilities.getServiceTypes();
	assertNotNull(serviceTypes);
	assertTrue(serviceTypes.size() > 0);
	for (String serviceType : serviceTypes) {
	    System.out.println(serviceType);
	}
    }

    @Test
    public void testGetServiceImplementations() {
	System.out.println("=================================");
	System.out.println("Available Cipher Implementations:");
	System.out.println("=================================");
	Set<String> cipherImplementations = SecurityUtilities
		.getServiceImplementations("Cipher");
	assertNotNull(cipherImplementations);
	assertTrue(cipherImplementations.size() > 0);
	for (String serviceType : cipherImplementations) {
	    System.out.println(serviceType);
	}
    }

}
