package com.puresoltechnologies.commons.utils.crypt;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import com.puresoltechnologies.commons.utils.crypt.SecurityUtilities;

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
	public void testGetCipherImplementations() {
		System.out.println("=================================");
		System.out.println("Available Cipher Implementations:");
		System.out.println("=================================");
		Set<String> implementations = SecurityUtilities
				.getServiceImplementations("Cipher");
		assertNotNull(implementations);
		assertTrue(implementations.size() > 0);
		for (String implementation : implementations) {
			System.out.println(implementation);
		}
	}

	@Test
	public void testGetMessageDigestImplementations() {
		System.out.println("========================================");
		System.out.println("Available MessageDigest Implementations:");
		System.out.println("========================================");
		Set<String> implementations = SecurityUtilities
				.getServiceImplementations("MessageDigest");
		assertNotNull(implementations);
		assertTrue(implementations.size() > 0);
		for (String implementation : implementations) {
			System.out.println(implementation);
		}
	}

	@Test
	public void testGetSignatureImplementations() {
		System.out.println("====================================");
		System.out.println("Available Signature Implementations:");
		System.out.println("====================================");
		Set<String> implementations = SecurityUtilities
				.getServiceImplementations("Signature");
		assertNotNull(implementations);
		assertTrue(implementations.size() > 0);
		for (String implementation : implementations) {
			System.out.println(implementation);
		}
	}

}
