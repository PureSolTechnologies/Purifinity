package com.puresol.coding.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.java.antlr.output.JavaLexer;
import com.puresol.coding.java.antlr.output.JavaParser;

import junit.framework.TestCase;

public class JavaAnalyserTest extends TestCase {

	@Test
	public void testJavaParser() throws IOException {
		Logger.getRootLogger().setLevel(Level.TRACE);
		File file = new File("test/com/puresol/coding/java/Test.java");
		//new JavaAnalyser(file);
		InputStream in = new FileInputStream(file);
		JavaLexer lexer = new JavaLexer(new ANTLRInputStream(in));
		CommonTokenStream cts = new CommonTokenStream(lexer);
		JavaParser parser = new JavaParser(cts);
		try {
			JavaParser.compilationUnit_return result = parser.compilationUnit();
		} catch (RecognitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
