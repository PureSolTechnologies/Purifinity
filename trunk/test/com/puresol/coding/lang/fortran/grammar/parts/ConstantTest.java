package com.puresol.coding.lang.fortran.grammar.parts;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class ConstantTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		assertTrue(GrammarPartTester.test("constant", "1.0"));
		assertTrue(GrammarPartTester.test("constant", "0"));
		assertTrue(GrammarPartTester.test("constant", "1.23"));
		assertTrue(GrammarPartTester.test("constant", "\"HALLO\""));
	}

}
