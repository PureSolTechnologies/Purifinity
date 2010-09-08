package com.puresol.coding.lang.fortran.grammar.parts;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class LanguageBindingSpecTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("language-binding-spec", "BIND(C)"));
		assertTrue(GrammarPartTester.test("language-binding-spec", "BIND(C,name=value)"));
	}

}
