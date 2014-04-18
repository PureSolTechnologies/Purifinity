package com.puresoltechnologies.purifinity.server.passwordstore.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.puresoltechnologies.purifinity.server.passwordstore.core.api.PasswordData;
import com.puresoltechnologies.purifinity.server.passwordstore.core.api.PasswordEncrypter;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordEncryptionException;

@RunWith(Arquillian.class)
public class PasswordEncryperBeanIT extends AbstractPasswordStoreServerTest {

	private static final int NUMBER_OF_ENCRYPTIONS = 1000;
	private static final double MILLISECONDS_IN_SECONDS = 1000.0;

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
		long start = System.currentTimeMillis();
		for (int i = 0; i < NUMBER_OF_ENCRYPTIONS; i++) {
			String password = "This is my password No. '" + i + "'!";
			PasswordData encryptPassword = encrypter.encryptPassword(password);
			System.out.println(encryptPassword);
			passwords.put(password, encryptPassword);
		}
		long stop = System.currentTimeMillis();
		double totalSeconds = (stop - start) / MILLISECONDS_IN_SECONDS;
		System.out.println(MessageFormat.format("time: {0} s", totalSeconds));
		double speed = NUMBER_OF_ENCRYPTIONS / totalSeconds;
		System.out.println(MessageFormat.format("speed: {0} encryptions/s",
				speed));
		assertTrue(
				"It is assumed that more than 200 encryptions are possible. It's not!",
				speed > 200);
	}

}
