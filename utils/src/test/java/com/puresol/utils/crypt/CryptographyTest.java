package com.puresol.utils.crypt;

import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Set;

import org.junit.Test;

public class CryptographyTest {

	@Test
	public void testRSA() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		assertNotNull(keyPairGenerator);
		keyPairGenerator.initialize(1024);
		KeyPair keyPair = keyPairGenerator.genKeyPair();
		assertNotNull(keyPair);
		PublicKey publicKey = keyPair.getPublic();
		assertNotNull(publicKey);
		PrivateKey privateKey = keyPair.getPrivate();
		assertNotNull(privateKey);

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		assertNotNull(keyFactory);
		RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey,
				RSAPrivateKeySpec.class);
		assertNotNull(privateKeySpec);
		RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(publicKey,
				RSAPublicKeySpec.class);
		assertNotNull(publicKeySpec);

		BigInteger privateModulus = privateKeySpec.getModulus();
		assertNotNull(privateModulus);
		BigInteger privateExponent = privateKeySpec.getPrivateExponent();
		assertNotNull(privateExponent);

		BigInteger publicModulus = publicKeySpec.getModulus();
		assertNotNull(publicModulus);
		BigInteger publicExponent = publicKeySpec.getPublicExponent();
		assertNotNull(publicExponent);

		System.out.println("privateModulus: " + privateModulus);
		System.out.println("privateExponent: " + privateExponent);
		System.out.println("publicModulus: " + publicModulus);
		System.out.println("publicExponent: " + publicExponent);
	}

	@Test
	public void testListAllCiphers() throws Exception {
		Provider[] providers = Security.getProviders();
		for (int providerId = 0; providerId < providers.length; providerId++) {
			Provider provider = providers[providerId];
			System.out.println("=================================");
			System.out.println("Provider " + provider.getName() + " ("
					+ providerId + ")");
			System.out.println("=================================");

			Set<Object> keys = provider.keySet();
			for (Object key : keys) {
				Object value = provider.get(key);
				System.out.println(key + " = " + value + " (" + key.getClass()
						+ "/" + value.getClass() + ")");
			}
		}
	}
}
