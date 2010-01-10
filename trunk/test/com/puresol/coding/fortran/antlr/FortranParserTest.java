package com.puresol.coding.fortran.antlr;

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
import com.puresol.coding.fortran.antlr.output.FortranLexer;
import com.puresol.coding.fortran.antlr.output.FortranParser;

import junit.framework.Assert;
import junit.framework.TestCase;

public class FortranParserTest extends TestCase {

	@Test
	public void testParser() {
		for (File file : FortranTestUtilities.getFiles()) {
			Assert.assertTrue(scanFile(file));
		}
	}

	private boolean scanFile(File file) {
		try {
			System.out.println("Scan file '" + file + "'");
			InputStream in = new FileInputStream(file);
			FortranLexer lexer = new FortranLexer(new ANTLRInputStream(in));
			FortranParser parser = new FortranParser(new CommonTokenStream(
					lexer));
			parser.file();
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
