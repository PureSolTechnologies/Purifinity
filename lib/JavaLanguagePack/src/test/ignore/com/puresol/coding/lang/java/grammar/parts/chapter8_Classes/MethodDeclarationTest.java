package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class MethodDeclarationTest {

	@Test
	public void test() {
		assertTrue(JavaGrammarPartTester.test("MethodDeclaration",
				"static public String getUserAgent() {\n" + "}\n"));
	}

	@Test
	public void test2() {
		assertTrue(JavaGrammarPartTester
				.test("MethodDeclaration",
						"static public String getUserAgent() {\n"
								+ "return \"JNLP/\" + JNLP_VERSION + \" javaws/\" + JAVAWS_VERSION + \" (\" + getBuildID() + \")\" + \" Java/\" + System.getProperty(\"java.version\");\n"
								+ "}\n"));
	}

	@Test
	public void test3() {
		assertTrue(JavaGrammarPartTester
				.test("MethodDeclaration",
						"static public String getBuildID() {\n"
								+ "	String build = null;\n"
								+ "InputStream s = Globals.class.getResourceAsStream(\"/build.id\");\n"
								+ "if (s != null) {\n"
								+ "BufferedReader br = new BufferedReader(new InputStreamReader(s));\n"
								// + "try {\n"
								+ "build = br.readLine();\n"
								// + "} catch(IOException e) { /* ignore */ }\n"
								+ "}\n"
								+ "return (build == null || build.length() == 0) ? \"<internal>\" : build;\n"
								+ "}"));
	}

	@Test
	public void test4() {
		assertTrue(JavaGrammarPartTester.test("MethodDeclaration",
				"public void setStops(GradientStop... stops) {\n" + "}"));
	}

}
