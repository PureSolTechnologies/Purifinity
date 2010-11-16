package com.puresol.uhura.lexer;

import static org.junit.Assert.*;

import org.junit.Test;

public class TokenStreamTest {

	@Test
	public void testInstance() {
		assertNotNull(new TokenStream("test.java"));
	}

	@Test
	public void testInitValues() {
		TokenStream stream = new TokenStream("test.java");
		assertEquals("test.java", stream.getName());
		assertEquals(0, stream.size());
	}

}
