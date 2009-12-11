package com.puresol.coding.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.java.antlr.output.JavaLexer;

import junit.framework.TestCase;

public class JavaAnalyserTest extends TestCase {

	@Test
	public void testJavaParser() throws IOException {
//		Logger.getRootLogger().setLevel(Level.TRACE);
		File file = new File("test/com/puresol/coding/java/Test.java");
		InputStream in = new FileInputStream(file);
		JavaLexer lexer = new JavaLexer(new ANTLRInputStream(in));
		CommonTokenStream stream = new CommonTokenStream(lexer);
//		for (Token token : (List<Token>)stream.getTokens()) {
//			System.out.println(token.getText());
//		}
		JavaAnalyser analyser = new JavaAnalyser(file);
	}
}
