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

	private File file = null;
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
		this.file = file;
		analyse();
	}

	public void analyse() {
		try {
			InputStream in = new FileInputStream(file);
			lexer = new JavaLexer(new ANTLRInputStream(in));
			CommonTokenStream cts = new CommonTokenStream(lexer);
			parser = new JavaParser(cts);
			visitor = new JavaTreeVisitor(parser);
			ArrayList<CodeRange> ranges = visitor.getCodeRanges();
			for (CodeRange range: ranges) {
				System.out.println(range.getText());
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (RecognitionException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public ArrayList<CodeRange> getCodeRanges() {
		return visitor.getCodeRanges();
	}

	public Language getLanguage() {
		return Language.JAVA;
	}

	public File getFile() {
		return file;
	}

	public static void main(String[] args) {
		File file = new File("src/com/puresol/coding/java/JavaAnalyser.java");
		new JavaAnalyser(file);
	}
}
