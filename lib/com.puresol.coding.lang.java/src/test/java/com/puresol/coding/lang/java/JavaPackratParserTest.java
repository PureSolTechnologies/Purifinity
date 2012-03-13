package com.puresol.coding.lang.java;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.parser.packrat.PackratParser;

public class JavaPackratParserTest {

    private String readToString(InputStream inputStream) throws Throwable {
	StringBuffer stringBuffer = new StringBuffer();
	InputStreamReader reader = new InputStreamReader(inputStream);
	try {
	    char[] buffer = new char[4096];
	    int length;
	    do {
		length = reader.read(buffer);
		if (length > 0) {
		    stringBuffer.append(buffer, 0, length);
		}
	    } while (length == buffer.length);
	} finally {
	    reader.close();
	}
	return stringBuffer.toString();
    }

    @Test
    public void testCompilationUnit() throws Throwable {
	Grammar grammar = JavaGrammar.getInstance().getGrammar();
	assertNotNull(grammar);
	PackratParser parser = new PackratParser(grammar);
	assertNotNull(parser.parse(
		"/* test */\n\npackage test.test2; class test { }", "TEST"));
    }

    @Test
    public void testArrayCreationExpression() throws Throwable {
	Grammar grammar = JavaGrammar.getInstance().getGrammar();
	assertNotNull(grammar);
	PackratParser parser = new PackratParser(grammar);
	assertNotNull(parser.parse("new a[1][2][]", "ArrayCreationExpression",
		"TEST"));
    }

    @Test
    public void testClassInstanceCreationExpression() throws Throwable {
	Grammar grammar = JavaGrammar.getInstance().getGrammar();
	assertNotNull(grammar);
	PackratParser parser = new PackratParser(grammar);
	assertNotNull(parser.parse("new A()",
		"ClassInstanceCreationExpression", "TEST"));
    }

    @Test
    public void testExpression() throws Throwable {
	Grammar grammar = JavaGrammar.getInstance().getGrammar();
	assertNotNull(grammar);
	PackratParser parser = new PackratParser(grammar);
	assertNotNull(parser.parse("\"string\"", "Expression", "TEST"));
	assertNotNull(parser.parse("1", "Expression", "TEST"));
	assertNotNull(parser.parse("1.23", "Expression", "TEST"));
	assertNotNull(parser.parse("new A()", "Expression", "TEST"));
	assertNotNull(parser.parse("new A(1)", "Expression", "TEST"));
	assertNotNull(parser.parse("new A(1, 2.3)", "Expression", "TEST"));
    }

    @Test
    public void testMethodInvokation() throws Throwable {
	Grammar grammar = JavaGrammar.getInstance().getGrammar();
	assertNotNull(grammar);
	PackratParser parser = new PackratParser(grammar);
	assertNotNull(parser.parse("method()", "MethodInvocation", "TEST"));
	assertNotNull(parser
		.parse("class.method()", "MethodInvocation", "TEST"));
	assertNotNull(parser
		.parse("super.method()", "MethodInvocation", "TEST"));
	assertNotNull(parser.parse("class.super.method()", "MethodInvocation",
		"TEST"));
	assertNotNull(parser.parse("class.method(\"1\", 2, 3.3)",
		"MethodInvocation", "TEST"));
    }

    @Test
    public void testLocalVariableDeclarationStatement() throws Throwable {
	Grammar grammar = JavaGrammar.getInstance().getGrammar();
	assertNotNull(grammar);
	PackratParser parser = new PackratParser(grammar);
	assertNotNull(parser.parse("A a = new A();",
		"LocalVariableDeclarationStatement", "TEST"));
	assertNotNull(parser.parse("B b = new B();",
		"LocalVariableDeclarationStatement", "TEST"));
	assertNotNull(parser.parse("char b[] = new char[1024];",
		"LocalVariableDeclarationStatement", "TEST"));
	assertNotNull(parser.parse("char[] b = new char[1024];",
		"LocalVariableDeclarationStatement", "TEST"));
    }

    @Test
    public void testBlockStatements() throws Throwable {
	Grammar grammar = JavaGrammar.getInstance().getGrammar();
	assertNotNull(grammar);
	PackratParser parser = new PackratParser(grammar);
	assertNotNull(parser.parse("int i;", "BlockStatements", "TEST"));
	assertNotNull(parser.parse("int i;int j;", "BlockStatements", "TEST"));
	assertNotNull(parser.parse("int i = 0;int j = 0;", "BlockStatements",
		"TEST"));
	assertNotNull(parser.parse("A a = new A();", "BlockStatements", "TEST"));
	assertNotNull(parser.parse("A a = new A[1]; B b = new B[1];",
		"BlockStatements", "TEST"));
	assertNotNull(parser.parse("A a = new A(1); B b = new B(2);",
		"BlockStatements", "TEST"));
	assertNotNull(parser.parse("A a = new A(); B b = new B();",
		"BlockStatements", "TEST"));
    }

    @Test
    public void testBlock() throws Throwable {
	Grammar grammar = JavaGrammar.getInstance().getGrammar();
	assertNotNull(grammar);
	PackratParser parser = new PackratParser(grammar);
	assertNotNull(parser.parse("{}", "Block", "TEST"));
	assertNotNull(parser.parse("{int i;}", "Block", "TEST"));
	assertNotNull(parser.parse("{int i;int j;}", "Block", "TEST"));
	assertNotNull(parser.parse("{int i = 0;int j = 0;}", "Block", "TEST"));
	assertNotNull(parser.parse("{A a = new A();}", "Block", "TEST"));
	assertNotNull(parser.parse("{A a = new A[1]; B b = new B[1];}",
		"BlockStatements", "TEST"));
	assertNotNull(parser.parse("{A a = new A(); B b = new B();}", "Block",
		"TEST"));
    }

    @Test
    public void testMethodBlock() throws Throwable {
	Grammar grammar = JavaGrammar.getInstance().getGrammar();
	assertNotNull(grammar);
	PackratParser parser = new PackratParser(grammar);
	assertNotNull(parser.parse(";", "MethodBody", "TEST"));
	assertNotNull(parser.parse("{}", "MethodBody", "TEST"));
    }

    @Test
    public void testJavaFile() throws Throwable {
	File file = new File(
		"src/test/java/com/puresol/coding/lang/java/JavaPackratParserTest.java");
	assertTrue(file.exists());
	String text = readToString(new FileInputStream(file));
	Grammar grammar = JavaGrammar.getInstance().getGrammar();
	assertNotNull(grammar);
	PackratParser parser = new PackratParser(grammar);
	ParserTree tree = parser.parse(text, "TEST");
	assertNotNull(tree);
    }
}
