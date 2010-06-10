package com.puresol.coding.lang.fortran;

import java.util.regex.Pattern;

import org.junit.Test;

import junit.framework.TestCase;

public class LexicalStructureTest extends TestCase {

	@Test
	public void testSignificant() {
		assertTrue(Pattern.matches(LexicalStructure.SIGNIFICANT, ".12345"));
		assertTrue(Pattern.matches(LexicalStructure.SIGNIFICANT, "12345."));
		assertTrue(Pattern.matches(LexicalStructure.SIGNIFICANT, "12345.12345"));
	}

	@Test
	public void testRealLiteralConstant() {
		assertTrue(Pattern.matches(LexicalStructure.REAL_LITERAL_CONSTANT,
				"12345.12345"));
		assertTrue(Pattern.matches(LexicalStructure.REAL_LITERAL_CONSTANT,
				"12345.12345E+1"));
		assertTrue(Pattern.matches(LexicalStructure.REAL_LITERAL_CONSTANT,
				"12345.12345E-1"));
		assertTrue(Pattern.matches(LexicalStructure.REAL_LITERAL_CONSTANT,
				"1E-1"));
		assertTrue(Pattern.matches(LexicalStructure.REAL_LITERAL_CONSTANT,
				"1E+1"));
	}

	@Test
	public void testSignedRealLiteralConstant() {
		assertTrue(Pattern.matches(
				LexicalStructure.SIGNED_REAL_LITERAL_CONSTANT, "+12345.12345"));
		assertTrue(Pattern.matches(
				LexicalStructure.SIGNED_REAL_LITERAL_CONSTANT,
				"-12345.12345E+1"));
		assertTrue(Pattern.matches(
				LexicalStructure.SIGNED_REAL_LITERAL_CONSTANT,
				"+12345.12345E-1"));
		assertTrue(Pattern.matches(
				LexicalStructure.SIGNED_REAL_LITERAL_CONSTANT, "-1E-1"));
		assertTrue(Pattern.matches(
				LexicalStructure.SIGNED_REAL_LITERAL_CONSTANT, "1E+1"));
	}

	@Test
	public void testCharLiteralConstant() {
		assertTrue(Pattern.matches(LexicalStructure.CHAR_LITERAL_CONSTANT,
				"\"\""));
		assertTrue(Pattern
				.matches(LexicalStructure.CHAR_LITERAL_CONSTANT, "''"));
		assertTrue(Pattern.matches(LexicalStructure.CHAR_LITERAL_CONSTANT,
				"\"String\""));
		assertTrue(Pattern.matches(LexicalStructure.CHAR_LITERAL_CONSTANT,
				"'String'"));
		assertTrue(Pattern.matches(LexicalStructure.CHAR_LITERAL_CONSTANT,
				"\"One \"\"String\"\"\""));
		assertTrue(Pattern.matches(LexicalStructure.CHAR_LITERAL_CONSTANT,
				"'One ''String'''"));
		assertTrue(Pattern.matches(LexicalStructure.CHAR_LITERAL_CONSTANT,
				"\"Don't\""));
		assertTrue(Pattern.matches(LexicalStructure.CHAR_LITERAL_CONSTANT,
				"'Don''t'"));
		assertTrue(Pattern.matches(LexicalStructure.CHAR_LITERAL_CONSTANT,
				"\"Don't\""));
		assertTrue(Pattern.matches(LexicalStructure.CHAR_LITERAL_CONSTANT,
				"'Don''t'"));
		assertFalse(Pattern.matches(LexicalStructure.CHAR_LITERAL_CONSTANT,
				"\"Don\"t\""));
		assertFalse(Pattern.matches(LexicalStructure.CHAR_LITERAL_CONSTANT,
				"'Don't'"));
	}
}
