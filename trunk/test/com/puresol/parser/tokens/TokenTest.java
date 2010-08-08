package com.puresol.parser.tokens;

import org.junit.Test;

import com.puresol.coding.tokentypes.Literal;
import com.puresol.parser.tokens.Token;
import com.puresol.parser.tokens.TokenCreationException;
import com.puresol.parser.tokens.TokenPublicity;
import com.puresol.testing.Tester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TokenTest extends TestCase {

	@Test
	public void testStandards() {
		Tester.testStandards(Token.class);
	}

	@Test
	public void testSettersAndGetters() {
		Tester.testGetterAndSetter(Token.class);
	}

	@Test
	public void testCreateByDefinitionClass() {
		String text = "Hello!\nThis is a test String!\nCiao!";
		try {
			Token token = Token
					.createByDefinition(Literal.class, 0, 0, 0, text);
			Assert.assertEquals(TokenPublicity.VISIBLE, token.getPublicity());
			Assert.assertEquals(text, token.getText());
			Assert.assertEquals(Literal.class, token.getDefinition());

			Literal definitionInstance = (Literal) token
					.getDefinitionInstance();
			Assert.assertNotNull(definitionInstance);
			Assert.assertEquals(Literal.class, definitionInstance.getClass());
		} catch (TokenCreationException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	@Test
	public void testCreatePrimitiveFromEmptyString() {
		Token token = Token.createPrimitiveFromString(0, 0, 0, "");
		Assert.assertEquals(TokenPublicity.VISIBLE, token.getPublicity());
		Assert.assertEquals("", token.getText());
		Assert.assertEquals(null, token.getDefinition());
		try {
			token.getDefinitionInstance();
			Assert.fail("TokenException was expected!");
		} catch (TokenCreationException e) {
			// exception is expected!
		}
	}

	@Test
	public void testCreatePrimitiveCRString() {
		Token token = Token.createPrimitiveFromString(0, 0, 0, "\n");
		Assert.assertEquals(TokenPublicity.VISIBLE, token.getPublicity());
		Assert.assertEquals("\n", token.getText());
		Assert.assertEquals(null, token.getDefinition());
		try {
			token.getDefinitionInstance();
			Assert.fail("TokenException was expected!");
		} catch (TokenCreationException e) {
			// exception is expected!
		}
	}
}
