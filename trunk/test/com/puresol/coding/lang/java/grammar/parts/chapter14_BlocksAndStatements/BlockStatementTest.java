package com.puresol.coding.lang.java.grammar.parts.chapter14_BlocksAndStatements;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class BlockStatementTest {

	@Test
	public void test1() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("BlockStatement", "a = b;"));
	}

	@Test
	public void test2() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("BlockStatement", "int a;"));
		assertTrue(GrammarPartTester.test("BlockStatement", "int a = 0;"));
		assertTrue(GrammarPartTester.test("BlockStatement",
				"String build = null;"));
	}

	@Test
	public void test3() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester
				.test("BlockStatement",
						"InputStream s = Globals.class.getResourceAsStream(\"/build.id\");"));
	}

	@Test
	public void test4() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("BlockStatement",
				"String build = null;"));
	}

}
