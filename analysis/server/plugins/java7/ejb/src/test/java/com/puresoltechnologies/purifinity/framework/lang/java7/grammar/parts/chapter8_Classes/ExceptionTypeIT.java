package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter8_Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammarPartTester;

public class ExceptionTypeIT {

	@Test
	public void testSingleException() throws Exception {
		assertTrue(JavaGrammarPartTester.test("ExceptionType", "Exception"));
	}

}
