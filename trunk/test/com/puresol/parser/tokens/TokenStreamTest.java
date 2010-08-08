package com.puresol.parser.tokens;

import java.io.File;

import org.junit.Test;

import com.puresol.coding.tokentypes.Comment;
import com.puresol.coding.tokentypes.Literal;
import com.puresol.parser.tokens.Token;
import com.puresol.parser.tokens.TokenCreationException;
import com.puresol.parser.tokens.TokenStream;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TokenStreamTest extends TestCase {

	public static TokenStream newTestTokenStream() {
		try {
			TokenStream tokenStream = new TokenStream(new File(
					"TestTokenStream"));
			int position = 0;
			for (int index = 0; index <= 20; index++) {
				String text = "Token" + index + "\n";
				Token token;
				if (index % 2 == 0) {
					token = Token.createByDefinition(Comment.class, index,
							position, index + 1, text);
				} else {
					token = Token.createByDefinition(Literal.class, index,
							position, index + 1, text);
				}
				tokenStream.addToken(token);
				position += token.getLength();
			}
			return tokenStream;
		} catch (TokenCreationException e) {
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
		TokenStream testStream = newTestTokenStream();
		Assert.assertEquals(21, testStream.getSize());
		Assert.assertEquals(21, testStream.getTokens().size());
		Assert.assertEquals("TestTokenStream", testStream.getFile().getPath());
	}

	@Test
	public void testGetReader() {
		// create token stream
		TokenStream tokenStream = TokenStreamTest.newTestTokenStream();
		assertNotNull(tokenStream);
		tokenStream.lock();
		// create reader
		TokenStreamIterator reader = new TokenStreamIterator(tokenStream);
		assertNotNull(reader);
	}

}
