package com.puresol.purifinity.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresol.purifinity.utils.StringUtils;

public class StringUtilsTest {

    @Test
    public void testConvertByteArrayToStringAndConvertStringToByteArray() {
	byte[] bytes = new byte[256];
	for (int i = 0; i < 256; i++) {
	    bytes[i] = (byte) i;
	}
	String convertedString = StringUtils.convertByteArrayToString(bytes);
	for (int i = 0; i < 16; i++) {
	    String iString = Integer.toHexString(i);
	    for (int j = 0; j < 16; j++) {
		String jString = Integer.toHexString(j);
		int pos = i * 16 + j;
		String substring = convertedString.substring(pos * 2,
			pos * 2 + 2);
		assertEquals(iString + jString, substring);
	    }
	}
	byte[] reconvertedByteArray = StringUtils
		.convertStringToByteArray(convertedString);
	assertEquals(bytes.length, reconvertedByteArray.length);
	for (int i = 0; i < bytes.length; i++) {
	    assertEquals(bytes[i], reconvertedByteArray[i]);
	    assertEquals((byte) i, reconvertedByteArray[i]);
	}
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertByteArrayToStringNullArgument() {
	StringUtils.convertByteArrayToString(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertStringToByteArrayNullArgument() {
	StringUtils.convertStringToByteArray(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertStringToByteArrayOddStringLength() {
	StringUtils.convertStringToByteArray("012");
    }

    @Test(expected = NumberFormatException.class)
    public void testConvertStringToByteArrayIllegalHex() {
	StringUtils.convertStringToByteArray("0123456789abcdefgh");
    }
}
