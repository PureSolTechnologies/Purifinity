package com.puresol.coding.java.antlr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import junit.framework.Assert;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.junit.Test;

import com.puresol.coding.ANTLRUtilities;
import com.puresol.coding.java.antlr.output.JavaLexer;

public class JavaLexerTest {

	@Test
	public void testLexer() {
		for (File file : JavaTestUtilities.getFiles()) {
			Assert.assertTrue(scanFile(file));
		}
	}

	private boolean scanFile(File file) {
		try {
			System.out.println("Scan file '" + file + "'");
			InputStream in = new FileInputStream(file);
			JavaLexer lexer = new JavaLexer(new ANTLRInputStream(in));
			ANTLRUtilities.printTokenStream(new CommonTokenStream(lexer),
					new PrintStream(new File("/dev/null")));
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
