package com.puresoltechnologies.purifinity.coding.lang.java7.grammar.parts.chapter14_BlocksAndStatements;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammarPartTester;

public class BlockStatementIT {

	@Test
	public void test1() throws Exception {
		assertTrue(JavaGrammarPartTester.test("BlockStatement", "a = b;"));
	}

	@Test
	public void test2() throws Exception {
		assertTrue(JavaGrammarPartTester.test("BlockStatement", "int a;"));
	}

	@Test
	public void test3() throws Exception {
		assertTrue(JavaGrammarPartTester.test("BlockStatement", "int a = 0;"));
	}

	@Test
	public void test4() throws Exception {
		assertTrue(JavaGrammarPartTester.test("BlockStatement",
				"String build = null;"));
	}

	@Test
	public void test5() throws Exception {
		assertTrue(JavaGrammarPartTester
				.test("BlockStatement",
						"InputStream s = Globals.class.getResourceAsStream(\"/build.id\");"));
	}

	@Test
	public void test6() throws Exception {
		assertTrue(JavaGrammarPartTester.test("BlockStatement",
				"String build = null;"));
	}

	@Test
	public void test7() throws Exception {
		assertTrue(JavaGrammarPartTester.test("BlockStatement",
				"String name = super.getName();"));
	}

}
