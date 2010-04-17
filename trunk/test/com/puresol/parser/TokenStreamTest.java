package com.puresol.parser;

import java.io.File;

import org.junit.Test;

import com.puresol.coding.lang.java.source.literals.IdLiteral;
import com.puresol.coding.tokentypes.Comment;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TokenStreamTest extends TestCase {

	public static TokenStream newTestTokenStream() {
		try {
			TokenStream tokenStream = new TokenStream(new File(
					"TestTokenStream"));
			int startPos = 0;
			int line = 0;
			for (int index = 0; index <= 20; index++) {
				String text = "Token" + index + "\n";
				Token token;
				if (index % 2 == 0) {
					token = Token.createByDefinition(Comment.class, index,
							startPos, line, text);
				} else {
					token = Token.createByDefinition(IdLiteral.class, index,
							startPos, line, text);
				}
				tokenStream.addToken(token);
				startPos += text.length();
				line++;
			}
			return tokenStream;
		} catch (TokenException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
		return null;
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
