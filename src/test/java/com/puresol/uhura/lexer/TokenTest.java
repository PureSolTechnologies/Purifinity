package com.puresol.uhura.lexer;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.uhura.grammar.token.Visibility;

public class TokenTest {

	@Test
	public void testInstance() {
		assertNotNull(new Token("Name", "Text", Visibility.VISIBLE,
				new TokenMetaData("test.java", 1, 2, 3, 4)));
	}

	@Test
	public void testInitialValues() {
		TokenMetaData metaData = new TokenMetaData("test.java", 1, 2, 3, 4);
		Token token = new Token("Name", "Text", Visibility.VISIBLE, metaData);
		assertEquals("Name", token.getName());
		assertEquals("Text", token.getText());
		assertEquals(Visibility.VISIBLE, token.getVisibility());
		assertSame(metaData, token.getMetaData());
	}

	@Test
	public void testClone() {
		Token token = new Token("Name", "Text", Visibility.VISIBLE,
				new TokenMetaData("test.java", 1, 2, 3, 4));
		Token cloned = token.clone();
		assertNotSame(token, cloned);
		assertEquals(token, cloned);
	}

}
