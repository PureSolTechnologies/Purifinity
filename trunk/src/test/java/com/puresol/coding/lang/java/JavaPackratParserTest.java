package com.puresol.coding.lang.java;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.trees.TreePrinter;
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
	public void testBasicNonComplex() throws Throwable {
		Grammar grammar = JavaGrammar.getInstance().getGrammar();
		assertNotNull(grammar);
		PackratParser parser = new PackratParser(grammar);
		ParserTree tree = parser.parse(
				"/* test */\n\npackage test.test2; class test { }", "TEST");
		assertNotNull(tree);
		TreePrinter printer = new TreePrinter(System.out);
		printer.println(tree);
	}

	@Test
	public void testBasic1() throws Throwable {
		Grammar grammar = JavaGrammar.getInstance().getGrammar();
		assertNotNull(grammar);
		PackratParser parser = new PackratParser(grammar);
		ParserTree tree = parser.parse(
				"new a[1][2][]",
				"ArrayCreationExpression", "TEST");
		assertNotNull(tree);
		TreePrinter printer = new TreePrinter(System.out);
		printer.println(tree);
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
		TreePrinter printer = new TreePrinter(System.out);
		printer.println(tree);
	}
}
