package com.puresol.coding.java.antlr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;

import com.puresol.coding.ANTLRUtilities;
import com.puresol.coding.java.antlr.output.JavaLexer;
import com.puresol.coding.java.antlr.output.JavaParser;

import junit.framework.Assert;
import junit.framework.TestCase;

public class JavaParserTest extends TestCase {

	@Test
	public void testParser() {
		for (File file : JavaTestUtilities.getFiles()) {
			Assert.assertTrue(scanFile(file));
		}
	}

	private boolean scanFile(File file) {
		try {
			System.out.println("Scan file '" + file + "'");
			InputStream in = new FileInputStream(file);
			JavaLexer lexer = new JavaLexer(new ANTLRInputStream(in));
			JavaParser parser = new JavaParser(new CommonTokenStream(lexer));
			parser.compilationUnit();
			// ANTLRUtilities.printTokenStream(parser.getTokenStream(),
			// new PrintStream(new File("/dev/null")));
			ANTLRUtilities.printTokenStream(parser.getTokenStream());
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RecognitionException e) {
			e.printStackTrace();
		}
		return false;
	}

}
