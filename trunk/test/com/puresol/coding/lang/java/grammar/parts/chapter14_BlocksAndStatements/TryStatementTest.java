package com.puresol.coding.lang.java.grammar.parts.chapter14_BlocksAndStatements;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class TryStatementTest {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("TryStatement", "try {\n"
				+ "} catch(Exception e) {\n" + "}\n"));
	}

	@Test
	public void test2() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("TryStatement", "try {\n"
				+ "test();\n" + "} catch (Exception e) {\n" + "test();\n"
				+ "}\n"));
	}

}
