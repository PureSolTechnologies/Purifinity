package com.puresol.utils;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

public class RegExpUtilitiesTest {

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
