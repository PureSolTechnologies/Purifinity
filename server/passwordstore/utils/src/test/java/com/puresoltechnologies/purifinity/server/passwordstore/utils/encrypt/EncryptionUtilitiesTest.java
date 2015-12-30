package com.puresoltechnologies.purifinity.server.passwordstore.utils.encrypt;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EncryptionUtilitiesTest {

	@Test
	public void testConvertHalfByteToHexCharacter() {
		assertEquals('0',
				EncryptionUtilities.convertHalfByteToHexCharacter((byte) 0));
		assertEquals('1',
				EncryptionUtilities.convertHalfByteToHexCharacter((byte) 1));
		assertEquals('2',
				EncryptionUtilities.convertHalfByteToHexCharacter((byte) 2));
		assertEquals('3',
				EncryptionUtilities.convertHalfByteToHexCharacter((byte) 3));
		assertEquals('4',
				EncryptionUtilities.convertHalfByteToHexCharacter((byte) 4));
		assertEquals('5',
				EncryptionUtilities.convertHalfByteToHexCharacter((byte) 5));
		assertEquals('6',
				EncryptionUtilities.convertHalfByteToHexCharacter((byte) 6));
		assertEquals('7',
				EncryptionUtilities.convertHalfByteToHexCharacter((byte) 7));
		assertEquals('8',
				EncryptionUtilities.convertHalfByteToHexCharacter((byte) 8));
		assertEquals('9',
				EncryptionUtilities.convertHalfByteToHexCharacter((byte) 9));
		assertEquals('a',
				EncryptionUtilities.convertHalfByteToHexCharacter((byte) 10));
		assertEquals('b',
				EncryptionUtilities.convertHalfByteToHexCharacter((byte) 11));
		assertEquals('c',
				EncryptionUtilities.convertHalfByteToHexCharacter((byte) 12));
		assertEquals('d',
				EncryptionUtilities.convertHalfByteToHexCharacter((byte) 13));
		assertEquals('e',
				EncryptionUtilities.convertHalfByteToHexCharacter((byte) 14));
		assertEquals('f',
				EncryptionUtilities.convertHalfByteToHexCharacter((byte) 15));
	}

	@Test
	public void testHexConvertCharacterToHalfByte() {
		assertEquals(0, EncryptionUtilities.convertHexCharacterToHalfByte('0'));
		assertEquals(1, EncryptionUtilities.convertHexCharacterToHalfByte('1'));
		assertEquals(2, EncryptionUtilities.convertHexCharacterToHalfByte('2'));
		assertEquals(3, EncryptionUtilities.convertHexCharacterToHalfByte('3'));
		assertEquals(4, EncryptionUtilities.convertHexCharacterToHalfByte('4'));
		assertEquals(5, EncryptionUtilities.convertHexCharacterToHalfByte('5'));
		assertEquals(6, EncryptionUtilities.convertHexCharacterToHalfByte('6'));
		assertEquals(7, EncryptionUtilities.convertHexCharacterToHalfByte('7'));
		assertEquals(8, EncryptionUtilities.convertHexCharacterToHalfByte('8'));
		assertEquals(9, EncryptionUtilities.convertHexCharacterToHalfByte('9'));
		assertEquals(10, EncryptionUtilities.convertHexCharacterToHalfByte('a'));
		assertEquals(11, EncryptionUtilities.convertHexCharacterToHalfByte('b'));
		assertEquals(12, EncryptionUtilities.convertHexCharacterToHalfByte('c'));
		assertEquals(13, EncryptionUtilities.convertHexCharacterToHalfByte('d'));
		assertEquals(14, EncryptionUtilities.convertHexCharacterToHalfByte('e'));
		assertEquals(15, EncryptionUtilities.convertHexCharacterToHalfByte('f'));
	}
}
