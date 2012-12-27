package com.puresol.coding.lang.java;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.parser.packrat.PackratParser;
import com.puresol.uhura.source.BuiltinSource;
import com.puresol.uhura.source.FileSource;
import com.puresol.uhura.source.Source;

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

    private PackratParser createParser() {
	Grammar grammar = JavaGrammar.getInstance();
	assertNotNull(grammar);
	PackratParser parser = new PackratParser(grammar);
	return parser;
    }

    private void check(String... text) throws ParserException, IOException {
	Source source = new BuiltinSource(text);
	PackratParser parser = createParser();
	assertNotNull(parser.parse(source.load()));
    }

    private void check(String text, String production) throws ParserException,
	    IOException {
	Source source = new BuiltinSource(text);
	PackratParser parser = createParser();
	assertNotNull(parser.parse(source.load(), production));
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
	File file = new File(
		"src/test/java/com/puresol/coding/lang/java/JavaPackratParserTest.java");
	assertTrue(file.exists());
	PackratParser parser = createParser();
	ParserTree tree = parser.parse(new FileSource(file).load());
	assertNotNull(tree);
    }
}
