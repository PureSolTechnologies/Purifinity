package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter8_Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammarPartTester;

public class SuperIT {

	@Test
	public void test() throws Exception {
		assertTrue(JavaGrammarPartTester.test("Super", "extends ClassName"));
	}
}
