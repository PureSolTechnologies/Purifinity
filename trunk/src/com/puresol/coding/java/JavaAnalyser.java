package com.puresol.coding.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.log4j.Logger;

import com.puresol.coding.AbstractAnalyser;
import com.puresol.coding.Language;
import com.puresol.coding.java.antlr.output.JavaLexer;
import com.puresol.coding.java.antlr.output.JavaParser;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaAnalyser extends AbstractAnalyser {

	private static final Logger logger = Logger.getLogger(JavaAnalyser.class);

	private JavaLexer lexer = null;
	private JavaParser parser = null;
	private JavaTreeVisitor visitor = null;

	public static boolean isSuitable(File file) {
		return file.getPath().endsWith(".java");
	}

	/**
	 * This is the default constructor.
	 * 
	 * @param A
	 *            file to be analysed.
	 */
	public JavaAnalyser(File file) {
		super(file);
		parse();
	}

	private void parse() {
		try {
			InputStream in = new FileInputStream(getFile());
			lexer = new JavaLexer(new ANTLRInputStream(in));
			CommonTokenStream cts = new CommonTokenStream(lexer);
			parser = new JavaParser(cts);
			visitor = new JavaTreeVisitor(parser);
			setCodeRanges(visitor.getCodeRanges());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (RecognitionException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public Language getLanguage() {
		return Language.JAVA;
	}
}
