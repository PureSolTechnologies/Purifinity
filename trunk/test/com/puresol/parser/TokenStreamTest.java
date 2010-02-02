package com.puresol.parser;

import java.io.File;

import org.junit.Test;

import com.puresol.coding.lang.java.source.literals.IdLiteral;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TokenStreamTest extends TestCase {

    public static TokenStream newTestTokenStream() {
	TokenStream tokenStream =
		new TokenStream(new File("TestTokenStream"));
	int startPos = 0;
	int line = 0;
	for (int index = 0; index <= 20; index++) {
	    String text = "Token" + index + "\n";
	    TokenPublicity publicity = TokenPublicity.VISIBLE;
	    if (index % 2 == 0) {
		publicity = TokenPublicity.HIDDEN;
	    }
	    Token token =
		    new Token(index, publicity, startPos, text.length(),
			    text, line, line + 1, IdLiteral.class);
	    tokenStream.addToken(token);
	    startPos += text.length();
	    line++;
	}
	return tokenStream;
    }

    @Test
    public void testConstructor() {
	TokenStream stream = new TokenStream(new File("File"));
	Assert.assertEquals("File", stream.getFile().getPath());
    }

    @Test
    public void testTestTokenStream() {
	try {
	    TokenStream testStream = newTestTokenStream();
	    Assert.assertEquals(1, testStream.getFirstVisbleTokenID());
	    Assert.assertEquals(21, testStream.getSize());
	    Assert.assertEquals(21, testStream.getTokens().size());
	    Assert.assertEquals("TestTokenStream", testStream.getFile()
		    .getPath());
	} catch (NoMatchingTokenException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	}
    }
}
