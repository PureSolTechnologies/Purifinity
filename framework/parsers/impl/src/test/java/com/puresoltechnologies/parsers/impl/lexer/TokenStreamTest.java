package com.puresoltechnologies.parsers.impl.lexer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.puresoltechnologies.parsers.impl.lexer.TokenStream;

public class TokenStreamTest {

    @Test
    public void testInstance() {
	assertNotNull(new TokenStream());
    }

    @Test
    public void testInitValues() {
	TokenStream stream = new TokenStream();
	assertEquals(0, stream.size());
    }

}
