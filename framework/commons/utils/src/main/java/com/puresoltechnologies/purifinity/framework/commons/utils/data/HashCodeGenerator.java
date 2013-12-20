/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.framework.commons.utils.data;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.misc.HashAlgorithm;
import com.puresoltechnologies.purifinity.framework.commons.utils.StringUtils;

public class HashCodeGenerator {

	public static String[] getServiceTypes() {
		Set<String> result = new HashSet<String>();
		for (Provider provider : Security.getProviders()) {
			for (Object providerKey : provider.keySet()) {
				String key = providerKey.toString();
				key = key.split(" ")[0];
				if (key.startsWith("Alg.Alias.")) {
					key = key.substring(10);
				}
				int ix = key.indexOf('.');
				result.add(key.substring(0, ix));
			}
		}
		return result.toArray(new String[result.size()]);
	}

	public static String[] getCryptoImpls(String serviceType) {
		Set<String> result = new HashSet<String>();

		for (Provider provider : Security.getProviders()) {
			for (Object providerKey : provider.keySet()) {
				String key = providerKey.toString();
				key = key.split(" ")[0];
				if (key.startsWith(serviceType + ".")) {
					result.add(key.substring(serviceType.length() + 1));
				} else if (key.startsWith("Alg.Alias." + serviceType + ".")) {
					result.add(key.substring(serviceType.length() + 11));
				}
			}
		}
		return result.toArray(new String[result.size()]);
	}

	public static String[] getCryptoImpls() {
		Set<String> result = new HashSet<String>();
		for (Provider provider : Security.getProviders()) {
			for (Object providerKey : provider.keySet()) {
				String key = providerKey.toString();
				key = key.split(" ")[0];
				result.add(key);
			}
		}
		return result.toArray(new String[result.size()]);
	}

	public static String get(HashAlgorithm algorithm, String line) {
		try {
			/* Calculation */
			MessageDigest digest = MessageDigest.getInstance(algorithm
					.getAlgorithmName());
			digest.reset();
			digest.update(line.getBytes());
			byte[] result = digest.digest();
			return StringUtils.convertByteArrayToString(result);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Something strange is going on in "
					+ algorithm + "module! Is there no " + algorithm
					+ "supported!?", e);
		}
	}

	public static String get(HashAlgorithm algorithm, ByteBuffer bytes) {
		try {
			/* Calculation */
			MessageDigest digest = MessageDigest.getInstance(algorithm
					.getAlgorithmName());
			digest.reset();
			digest.update(bytes);
			byte[] result = digest.digest();
			return StringUtils.convertByteArrayToString(result);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Something strange is going on in "
					+ algorithm + "module! Is there no " + algorithm
					+ "supported!?", e);
		}
	}

	public static String getMD5(String line) {
		return get(HashAlgorithm.MD5, line);
	}

	public static String getSHA(String line) {
		return get(HashAlgorithm.SHA1, line);
	}

	public static String getSHA256(String line) {
		return get(HashAlgorithm.SHA256, line);
	}

	public static String getSHA384(String line) {
		return get(HashAlgorithm.SHA384, line);
	}

	public static String getSHA512(String line) {
		return get(HashAlgorithm.SHA512, line);
	}

}
