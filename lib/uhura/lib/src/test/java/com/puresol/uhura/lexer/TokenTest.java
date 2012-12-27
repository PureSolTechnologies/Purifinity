package com.puresol.uhura.lexer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.source.UnspecifiedSource;

public class TokenTest {

    @Test
    public void testInstance() {
	assertNotNull(new Token("Name", "Text", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSource(), 1, 2, 3, 4)));
    }

    @Test
    public void testInitialValues() {
	TokenMetaData metaData = new TokenMetaData(new UnspecifiedSource(), 1,
		2, 3, 4);
	Token token = new Token("Name", "Text", Visibility.VISIBLE, metaData);
	assertEquals("Name", token.getName());
	assertEquals("Text", token.getText());
	assertEquals(Visibility.VISIBLE, token.getVisibility());
	assertSame(metaData, token.getMetaData());
    }

    @Test
    public void testClone() {
	Token token = new Token("Name", "Text", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSource(), 1, 2, 3, 4));
	Token cloned = token.clone();
	assertNotSame(token, cloned);
	assertEquals(token, cloned);
    }

}
