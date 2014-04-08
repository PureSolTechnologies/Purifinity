package com.puresol.passwordstore.core.impl;

import java.util.Random;

/**
 * This class provides some helper functions which are used for the password
 * store.
 * 
 * @author Rick-Rainer Ludwig
 */
public class EncryptionUtilities {

	private static final Random random = new Random(System.nanoTime());

	/**
	 * This function generates a specified number of random bytes. These bytes
	 * can be used for security keys.
	 * 
	 * @param num
	 * @return
	 */
	public static byte[] generateRandomBytes(int num) {
		byte[] randomBytes = new byte[num];
		random.nextBytes(randomBytes);
		return randomBytes;
	}

	/**
	 * This method converts an byte array into a letter string in lower case hex
	 * characters with double the length of the array.
	 * 
	 * @param b
	 * @return
	 */
	public static String convertBytesToHexString(byte[] key) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < key.length; i++) {
			buffer.append(convertByteToHexString(key[i]));
		}
		return buffer.toString();
	}

	/**
	 * This method converts a single byte into a two letter string in lower case
	 * hex characters.
	 * 
	 * @param b
	 * @return
	 */
	static String convertByteToHexString(byte b) {
		byte lowHalf = (byte) (b & 0x0f);
		byte highHalf = (byte) (b >> 4 & (~0xf0));
		char[] chars = new char[2];
		chars[0] = convertHalfByteToHexCharacter(highHalf);
		chars[1] = convertHalfByteToHexCharacter(lowHalf);
		return new String(chars);
	}

	/**
	 * Convert half byte (number from 0 to 15) into a single hex digit in lower
	 * case.
	 * 
	 * @param halfByte
	 * @return
	 */
	static char convertHalfByteToHexCharacter(byte halfByte) {
		if ((halfByte < 0) || (halfByte > 15))
			throw new IllegalArgumentException(
					"Half bytes need to be >=0 and <=15!");
		if (halfByte < 10) {
			return (char) ('0' + halfByte);
		} else {
			return (char) ('a' + halfByte - 10);
		}
	}

	public static byte[] convertStringToBytes(String string) {
		if (string.length() % 2 != 0) {
			throw new IllegalArgumentException(
					"Illegal HEX string '"
							+ string
							+ "' provided! The number of character needs to be dividable by 2.");
		}
		byte[] result = new byte[string.length() / 2];
		char[] chars = new char[string.length()];
		string.getChars(0, string.length(), chars, 0);
		for (int i = 0; i < string.length() / 2; i++) {
			char c = chars[2 * i];
			char c2 = chars[2 * i + 1];
			byte highHalfByte = convertHexCharacterToHalfByte(c);
			byte lowHalfByte = convertHexCharacterToHalfByte(c2);
			byte b = (byte) (highHalfByte * 16 + lowHalfByte);
			result[i] = b;
		}
		return result;
	}

	static byte convertHexCharacterToHalfByte(char character) {
		if ((character >= '0') && (character <= '9')) {
			return (byte) (character - '0');
		} else if ((character >= 'a') && (character <= 'f')) {
			return (byte) (character - 'a' + 10);
		} else if ((character >= 'A') && (character <= 'F')) {
			return (byte) (character - 'A' + 10);
		} else {
			throw new IllegalArgumentException("'" + character
					+ "' is an illegal character in HEX code!");
		}
	}

}
