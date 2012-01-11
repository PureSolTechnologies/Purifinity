package com.puresol.coding.lang.java.grammar.parts.chapter15_Expressions;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class PrimaryTest {

	@Test
	public void testConstants() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(JavaGrammarPartTester.test("Primary", "this"));
	}
}
