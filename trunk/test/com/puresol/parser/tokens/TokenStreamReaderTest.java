package com.puresol.parser.tokens;

import org.junit.Test;

import junit.framework.TestCase;

public class TokenStreamReaderTest extends TestCase {

	@Test
	public void testReaderInstanciation() {
		// create token stream
		TokenStream tokenStream = TokenStreamTest.newTestTokenStream();
		assertNotNull(tokenStream);
		tokenStream.lock();
		// create reader
		TokenStreamIterator reader = new TokenStreamIterator(tokenStream);
		assertNotNull(reader);
		// test base information
		assertEquals(0, reader.getPosition());
		assertEquals(tokenStream.getSize(), reader.getSize());
		assertTrue(reader.canGoForward());
		assertFalse(reader.isEOS());
		assertFalse(reader.canGoBackward());
	}

}
