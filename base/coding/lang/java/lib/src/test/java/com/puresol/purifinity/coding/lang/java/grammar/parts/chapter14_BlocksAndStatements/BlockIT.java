package com.puresol.purifinity.coding.lang.java.grammar.parts.chapter14_BlocksAndStatements;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.java.grammar.JavaGrammar;
import com.puresol.purifinity.coding.lang.java.grammar.JavaGrammarPartTester;
import com.puresol.purifinity.uhura.grammar.Grammar;
import com.puresol.purifinity.uhura.parser.packrat.PackratParser;

public class BlockIT {

	@Test
	public void testEmptyBlock() throws Exception {
		assertTrue(JavaGrammarPartTester.test("Block", "{}"));
	}

	@Test
	public void testAssignmentBlock() throws Exception {
		assertTrue(JavaGrammarPartTester.test("Block", "{ a = b; }"));
	}

	@Test
	public void testVariableDeclarationBlock() throws Exception {
		assertTrue(JavaGrammarPartTester
				.test("Block", "{ String str = null; }"));
	}

	@Test
	public void testComplexBlock() throws Exception {
		assertTrue(JavaGrammarPartTester
				.test("Block",
						"{\n"
								+ "	String build = null;\n"
								+ "InputStream s = Globals.class.getResourceAsStream(\"/build.id\");\n"
								+ "if (s != null) {\n"
								+ "BufferedReader br = new BufferedReader(new InputStreamReader(s));\n"
								+ "try {\n"
								+ "build = br.readLine();\n"
								+ "} catch(IOException e) { /* ignore */ }\n"
								+ "}\n"
								+ "return (build == null || build.length() == 0) ? \"<internal>\" : build;\n"
								+ "}"));
	}

	@Test
	public void test() throws Exception {
		Grammar grammar = JavaGrammar.getGrammar();
		PackratParser parser = new PackratParser(grammar);
		assertTrue(JavaGrammarPartTester.test("Block", "{\n" + //
				"	renderer.setGrammar(ast);\n" + //
				"	// preferredSize.height *= 0.7;\n" + //
				"	// preferredSize.width *= 0.7;\n" + //
				"	setSize(preferredSize.width, preferredSize.height);\n" + //
				"		getParent().redraw();\n" + //
				"}\n"));
	}
}
