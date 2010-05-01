package com.puresol.coding.lang.java;

import java.util.regex.Pattern;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

public class LexicalStructureTest extends TestCase {

	@Test
	public void testSignedInteger() {
		Pattern pattern = Pattern.compile(LexicalStructure.SIGNED_INTEGER);
		Assert.assertTrue(pattern.matcher("+1").matches());
		Assert.assertTrue(pattern.matcher("-1").matches());
		Assert.assertTrue(pattern.matcher("1").matches());
		Assert.assertTrue(pattern.matcher("-1").matches());
		Assert.assertTrue(pattern.matcher("123").matches());
	}

	@Test
	public void testExponentPart() {
		Pattern pattern = Pattern.compile(LexicalStructure.EXPONENT_PART);
		Assert.assertTrue(pattern.matcher("e+1").matches());
		Assert.assertTrue(pattern.matcher("E-1").matches());
	}

	@Test
	public void testEscapeSequence() {
		Pattern pattern = Pattern.compile(LexicalStructure.ESCAPE_SEQUENCE);
		Assert.assertTrue(pattern.matcher("\\b").matches());
		Assert.assertTrue(pattern.matcher("\\t").matches());
		Assert.assertTrue(pattern.matcher("\\n").matches());
		Assert.assertTrue(pattern.matcher("\\f").matches());
		Assert.assertTrue(pattern.matcher("\\r").matches());
		Assert.assertTrue(pattern.matcher("\\\"").matches());
		Assert.assertTrue(pattern.matcher("\\'").matches());
		Assert.assertTrue(pattern.matcher("\\\\").matches());
	}

	@Test
	public void testSeparators() {
		Pattern pattern = Pattern.compile(LexicalStructure.SEPARATORS);
		Assert.assertTrue(pattern.matcher("(").matches());
		Assert.assertTrue(pattern.matcher(")").matches());
		Assert.assertTrue(pattern.matcher("[").matches());
		Assert.assertTrue(pattern.matcher("]").matches());
		Assert.assertTrue(pattern.matcher("{").matches());
		Assert.assertTrue(pattern.matcher("}").matches());
		Assert.assertTrue(pattern.matcher(";").matches());
		Assert.assertTrue(pattern.matcher(",").matches());
		Assert.assertTrue(pattern.matcher(".").matches());
	}
}
