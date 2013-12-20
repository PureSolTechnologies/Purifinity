package com.puresoltechnologies.purifinity.framework.commons.utils.crypt;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.commons.utils.crypt.InputStreamScrambler;
import com.puresoltechnologies.purifinity.framework.commons.utils.crypt.OutputStreamScrambler;

public class OutputStreamScramblerTest {

	@Test
	public void test() throws IOException {
		String pattern = "1234567890";
		String testString = "This is the test input.";
		byte[] bytes = testString.getBytes();
		// Encoding
		ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
		InputStreamScrambler scrambler = new InputStreamScrambler(
				pattern.getBytes(), inputStream);
		byte[] scrambledBytes = IOUtils.toByteArray(scrambler);
		assertNotSame(bytes, scrambledBytes);
		String scrambledString = new String(scrambledBytes);
		assertFalse(scrambledString.equals(testString));

		// Decoding
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		OutputStreamScrambler outputScrambler = new OutputStreamScrambler(
				pattern.getBytes(), outputStream);
		IOUtils.write(scrambledBytes, outputScrambler);
		byte[] unscrambled = outputStream.toByteArray();
		assertNotSame(bytes, unscrambled);
		assertArrayEquals(bytes, unscrambled);
	}
}
