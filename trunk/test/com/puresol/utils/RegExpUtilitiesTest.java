package com.puresol.utils;

import java.util.regex.Pattern;

import org.junit.Test;

import junit.framework.TestCase;

public class RegExpUtilitiesTest extends TestCase {

	@Test
	public void testString2RegExp() {
		/*
		 * start and end tag...
		 */
		assertTrue(Pattern.matches(RegExpUtilities.string2RegExp("^"), "^"));
		assertTrue(Pattern.matches(RegExpUtilities.string2RegExp("$"), "$"));
		/*
		 * quantifiers, back slash and joker...
		 */
		assertTrue(Pattern.matches(RegExpUtilities.string2RegExp("+"), "+"));
		assertTrue(Pattern.matches(RegExpUtilities.string2RegExp("*"), "*"));
		assertTrue(Pattern.matches(RegExpUtilities.string2RegExp("?"), "?"));
		assertTrue(Pattern.matches(RegExpUtilities.string2RegExp("\\"), "\\"));
		assertTrue(Pattern.matches(RegExpUtilities.string2RegExp("."), "."));
		/*
		 * parenthesis, brackets...
		 */
		assertTrue(Pattern.matches(RegExpUtilities.string2RegExp("("), "("));
		assertTrue(Pattern.matches(RegExpUtilities.string2RegExp(")"), ")"));
		assertTrue(Pattern.matches(RegExpUtilities.string2RegExp("["), "["));
		assertTrue(Pattern.matches(RegExpUtilities.string2RegExp("]"), "]"));
		assertTrue(Pattern.matches(RegExpUtilities.string2RegExp("{"), "{"));
		assertTrue(Pattern.matches(RegExpUtilities.string2RegExp("}"), "}"));
	}

}
