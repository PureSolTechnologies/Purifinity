package com.puresol.coding.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.log4j.Logger;

import com.puresol.coding.Analyser;
import com.puresol.coding.CodeRange;
import com.puresol.coding.Language;
import com.puresol.coding.java.antlr.output.JavaLexer;
import com.puresol.coding.java.antlr.output.JavaParser;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaAnalyser implements Analyser {

	private static final Logger logger = Logger.getLogger(JavaAnalyser.class);

	private File file;
	private JavaLexer lexer;
	private JavaParser parser;

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
		this.file = file;
		analyse();
	}

	public void analyse() {
		try {
			InputStream in = new FileInputStream(file);
			lexer = new JavaLexer(new ANTLRInputStream(in));
			CommonTokenStream cts = new CommonTokenStream(lexer);
			parser = new JavaParser(cts);
			parser.file();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (RecognitionException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public ArrayList<CodeRange> getCodeRanges() {
		return parser.getParserHelper().getCodeRanges();
	}

	public Language getLanguage() {
		return Language.JAVA;
	}

	public File getFile() {
		return file;
	}
}
