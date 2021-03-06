package com.puresoltechnologies.purifinity.framework.lang.java7;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import com.puresoltechnologies.parsers.grammar.Grammar;
import com.puresoltechnologies.parsers.parser.ParseTreeNode;
import com.puresoltechnologies.parsers.parser.ParserException;
import com.puresoltechnologies.parsers.parser.packrat.PackratParser;
import com.puresoltechnologies.parsers.source.FixedCodeLocation;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.source.SourceFileLocation;
import com.puresoltechnologies.purifinity.server.plugin.java7.grammar.JavaGrammar;

public class JavaPackratParserTest {

	private PackratParser createParser() {
		Grammar grammar = JavaGrammar.getInstance();
		assertNotNull(grammar);
		PackratParser parser = new PackratParser(grammar);
		return parser;
	}

	private void check(String... text) throws ParserException, IOException {
		SourceCodeLocation source = new FixedCodeLocation(text);
		PackratParser parser = createParser();
		assertNotNull(parser.parse(source.getSourceCode()));
	}

	private void check(String text, String production) throws ParserException,
			IOException {
		SourceCodeLocation source = new FixedCodeLocation(text);
		PackratParser parser = createParser();
		assertNotNull(parser.parse(source.getSourceCode(), production));
	}

	@Test
	public void testCompilationUnit() throws Throwable {
		check("/* test */\n", "\n", "package test.test2; class test { }");
	}

	@Test
	public void testArrayCreationExpression() throws Throwable {
		check("new a[1][2][]", "ArrayCreationExpression");
	}

	@Test
	public void testClassInstanceCreationExpression() throws Throwable {
		check("new A()", "ClassInstanceCreationExpression");
	}

	@Test
	public void testExpression() throws Throwable {
		check("\"string\"", "Expression");
		check("1", "Expression");
		check("1.23", "Expression");
		check("new A()", "Expression");
		check("new A(1)", "Expression");
		check("new A(1, 2.3)", "Expression");
	}

	@Test
	public void testMethodInvokation() throws Throwable {
		check("method()", "MethodInvocation");
		check("class.method()", "MethodInvocation");
		check("super.method()", "MethodInvocation");
		check("class.super.method()", "MethodInvocation");
		check("class.method(\"1\", 2, 3.3)", "MethodInvocation");
	}

	@Test
	public void testLocalVariableDeclarationStatement() throws Throwable {
		check("A a = new A();", "LocalVariableDeclarationStatement");
		check("B b = new B();", "LocalVariableDeclarationStatement");
		check("char b[] = new char[1024];", "LocalVariableDeclarationStatement");
		check("char[] b = new char[1024];", "LocalVariableDeclarationStatement");
	}

	@Test
	public void testBlockStatements() throws Throwable {
		check("int i;", "BlockStatements");
		check("int i;int j;", "BlockStatements");
		check("int i = 0;int j = 0;", "BlockStatements");
		check("A a = new A();", "BlockStatements");
		check("A a = new A[1]; B b = new B[1];", "BlockStatements");
		check("A a = new A(1); B b = new B(2);", "BlockStatements");
		check("A a = new A(); B b = new B();", "BlockStatements");
	}

	@Test
	public void testBlock() throws Throwable {
		check("{}", "Block");
		check("{int i;}", "Block");
		check("{int i;int j;}", "Block");
		check("{int i = 0;int j = 0;}", "Block");
		check("{A a = new A();}", "Block");
		check("{A a = new A[1]; B b = new B[1];}", "BlockStatements");
		check("{A a = new A(); B b = new B();}", "Block");
	}

	@Test
	public void testMethodBlock() throws Throwable {
		check(";", "MethodBody");
		check("{}", "MethodBody");
	}

	@Test
	public void testJavaFile() throws Throwable {
		PackratParser parser = createParser();
		ParseTreeNode tree = parser
				.parse(new SourceFileLocation(
						"src/test/java/com/puresoltechnologies/purifinity/framework/lang/java7",
						"JavaPackratParserTest.java").getSourceCode());
		assertNotNull(tree);
	}
}
