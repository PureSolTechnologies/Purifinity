package com.puresol.io;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

public class StringOutputStreamTest {

	@Test
	public void testInstance() {
		assertNotNull(new StringOutputStream());
	}

	@Test
	public void testInitValues() {
		OutputStream outputStream = new StringOutputStream();
		assertEquals("", outputStream.toString());
	}

	@Test
	public void testWrite() {
		try {
			OutputStream outputStream = new StringOutputStream();
			outputStream.write(new String("test").getBytes());
			assertEquals("test", outputStream.toString());
			outputStream.write((char)65);
			assertEquals("testA", outputStream.toString());
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
