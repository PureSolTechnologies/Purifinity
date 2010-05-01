package com.puresol.parser;

import org.junit.Test;

import com.puresol.coding.lang.java.source.literals.Identifier;
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
			Token token = Token.createByDefinition(Identifier.class, 42, 123,
					21, text);
			Assert.assertEquals(42, token.getTokenID());
			Assert.assertEquals(TokenPublicity.VISIBLE, token.getPublicity());
			Assert.assertEquals(123, token.getStartPos());
			Assert.assertEquals(text, token.getText());
			Assert.assertEquals(text.length(), token.getLength());
			Assert.assertEquals(21, token.getStartLine());
			Assert.assertEquals(23, token.getStopLine());
			Assert.assertEquals(Identifier.class, token.getDefinition());

			Identifier definitionInstance = (Identifier) token
					.getDefinitionInstance();
			Assert.assertNotNull(definitionInstance);
			Assert.assertEquals(Identifier.class, definitionInstance.getClass());
		} catch (TokenException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	@Test
	public void testCreatePrimitiveFromEmptyString() {
		Token token = Token.createPrimitiveFromString(0, 0, 0, "");
		Assert.assertEquals(0, token.getTokenID());
		Assert.assertEquals(TokenPublicity.VISIBLE, token.getPublicity());
		Assert.assertEquals(0, token.getStartPos());
		Assert.assertEquals("", token.getText());
		Assert.assertEquals(0, token.getLength());
		Assert.assertEquals(0, token.getStartLine());
		Assert.assertEquals(0, token.getStopLine());
		Assert.assertEquals(null, token.getDefinition());
		try {
			token.getDefinitionInstance();
			Assert.fail("TokenException was expected!");
		} catch (TokenException e) {
			// exception is expected!
		}
	}

	@Test
	public void testCreatePrimitiveCRString() {
		Token token = Token.createPrimitiveFromString(0, 0, 0, "\n");
		Assert.assertEquals(0, token.getTokenID());
		Assert.assertEquals(TokenPublicity.VISIBLE, token.getPublicity());
		Assert.assertEquals(0, token.getStartPos());
		Assert.assertEquals("\n", token.getText());
		Assert.assertEquals(1, token.getLength());
		Assert.assertEquals(0, token.getStartLine());
		Assert.assertEquals(1, token.getStopLine());
		Assert.assertEquals(null, token.getDefinition());
		try {
			token.getDefinitionInstance();
			Assert.fail("TokenException was expected!");
		} catch (TokenException e) {
			// exception is expected!
		}
	}
}
