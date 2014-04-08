package com.puresol.passwordstore.test;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.puresol.passwordstore.core.api.PasswordData;
import com.puresol.passwordstore.core.api.PasswordEncrypter;
import com.puresol.passwordstore.domain.PasswordEncryptionException;

@RunWith(Arquillian.class)
public class PasswordEncryperBeanIT extends AbstractPasswordStoreServerTest {

	@Inject
	private PasswordEncrypter encrypter;

	@Before
	public void setup() {
		assertNotNull(encrypter);
	}

	@Test
	public void testSingleEncryption() throws PasswordEncryptionException {
		PasswordData passwordData = encrypter
				.encryptPassword("This is my password!");
		assertNotNull(passwordData);
		System.out.println(passwordData.getEncryptedPassword());
	}

	@Test
	public void test1000Encryptions() throws PasswordEncryptionException {
		Map<String, PasswordData> passwords = new HashMap<String, PasswordData>();
		long start = System.nanoTime();
		for (int i = 0; i < 1000; i++) {
			String password = "This is my password No. '" + i + "'!";
			PasswordData encryptPassword = encrypter.encryptPassword(password);
			System.out.println(encryptPassword);
			passwords.put(password, encryptPassword);
		}
		long stop = System.nanoTime();
		double totalMilliseconds = (stop - start) / 1000000.0;
		System.out.println("stop - start: " + totalMilliseconds);
		System.out.println("stop - start: " + 1000.0 / totalMilliseconds);
	}

}
