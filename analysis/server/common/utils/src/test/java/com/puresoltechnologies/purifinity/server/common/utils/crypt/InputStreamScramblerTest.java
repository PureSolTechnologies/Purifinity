package com.puresoltechnologies.purifinity.server.common.utils.crypt;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class InputStreamScramblerTest {

	@Test
	public void test() throws IOException {
		String pattern = "1234567890";
		String testString = "This is the test input.";
		byte[] bytes = testString.getBytes();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
		InputStreamScrambler scrambler = new InputStreamScrambler(
				pattern.getBytes(), inputStream);
		byte[] scrambledBytes = IOUtils.toByteArray(scrambler);
		assertNotSame(bytes, scrambledBytes);
		assertFalse(scrambledBytes.equals(bytes));
	}
}
