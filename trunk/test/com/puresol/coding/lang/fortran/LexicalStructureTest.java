package com.puresol.coding.lang.fortran;

import java.util.regex.Pattern;

import org.junit.Test;

import junit.framework.TestCase;

public class LexicalStructureTest extends TestCase {

	@Test
	public void testSignificant() {
		Pattern.matches(LexicalStructure.SIGNIFICANT, ".12345");
		Pattern.matches(LexicalStructure.SIGNIFICANT, "12345.");
		Pattern.matches(LexicalStructure.SIGNIFICANT, "12345.12345");
	}

	@Test
	public void testRealLiteralConstant() {
		Pattern.matches(LexicalStructure.REAL_LITERAL_CONSTANT, "12345.12345");
		Pattern.matches(LexicalStructure.REAL_LITERAL_CONSTANT,
				"12345.12345E+1");
		Pattern.matches(LexicalStructure.REAL_LITERAL_CONSTANT,
				"12345.12345e-1");
		Pattern.matches(LexicalStructure.REAL_LITERAL_CONSTANT, "1e-1");
		Pattern.matches(LexicalStructure.REAL_LITERAL_CONSTANT, "1E+1");
	}

	@Test
	public void testSignedRealLiteralConstant() {
		Pattern.matches(LexicalStructure.SIGNED_REAL_LITERAL_CONSTANT,
				"+12345.12345");
		Pattern.matches(LexicalStructure.SIGNED_REAL_LITERAL_CONSTANT,
				"-12345.12345E+1");
		Pattern.matches(LexicalStructure.SIGNED_REAL_LITERAL_CONSTANT,
				"+12345.12345e-1");
		Pattern.matches(LexicalStructure.SIGNED_REAL_LITERAL_CONSTANT, "-1e-1");
		Pattern.matches(LexicalStructure.SIGNED_REAL_LITERAL_CONSTANT, "1E+1");
	}
}
