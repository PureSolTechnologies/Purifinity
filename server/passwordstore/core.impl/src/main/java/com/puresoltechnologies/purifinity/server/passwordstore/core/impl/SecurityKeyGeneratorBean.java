package com.puresoltechnologies.purifinity.server.passwordstore.core.impl;

import javax.ejb.Stateless;


/**
 * This bean manages the creation and
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
@Stateless
public class SecurityKeyGeneratorBean implements SecurityKeyGenerator {

	@Override
	public String generate() {
		byte[] randomBytes = EncryptionUtilities.generateRandomBytes(32);
		return EncryptionUtilities.convertBytesToHexString(randomBytes);
	}
}
