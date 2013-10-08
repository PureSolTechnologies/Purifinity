package com.puresol.purifinity.coding.lang.java.grammar.parts.chapter14_BlocksAndStatements;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.java.grammar.JavaGrammarPartTester;

public class StatementIT {

	@Test
	public void test1() throws Exception {
		assertTrue(JavaGrammarPartTester.test("Statement", "a = b;"));
	}

	@Test
	public void test2() throws Exception {
		assertTrue(JavaGrammarPartTester.test("Statement",
				"function(LayoutPathImpl.SegmentPath.this);"));
	}

	@Test
	public void test3() throws Exception {
		assertTrue(JavaGrammarPartTester.test("Statement",
				"getParent().redraw();"));
	}
}
