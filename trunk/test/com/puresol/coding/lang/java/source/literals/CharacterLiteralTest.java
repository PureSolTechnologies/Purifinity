package com.puresol.coding.lang.java.source.literals;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CharacterLiteralTest extends TestCase {

	@Test
	public void test() {
		CharacterLiteral characterLiteral = new CharacterLiteral();
		Assert.assertTrue(characterLiteral.atStart("'c'"));
		Assert.assertTrue(characterLiteral.atStart("'\\b'"));
		Assert.assertTrue(characterLiteral.atStart("'\\t'"));
		Assert.assertTrue(characterLiteral.atStart("'\\n'"));
		Assert.assertTrue(characterLiteral.atStart("'\\f'"));
		Assert.assertTrue(characterLiteral.atStart("'\\r'"));
		Assert.assertTrue(characterLiteral.atStart("'\\\"'"));
		Assert.assertTrue(characterLiteral.atStart("'\\''"));
		Assert.assertTrue(characterLiteral.atStart("'\\\\'"));

		Assert.assertTrue(characterLiteral.atStart("'\\02'"));
}
}
