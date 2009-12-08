package com.puresol.coding.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.puresol.coding.HalsteadMetric;
import com.puresol.coding.SLOCStatistics;
import com.puresol.coding.SourceCodeAnalyser;
import com.puresol.coding.java.antlr.JavaLexerHelper;
import com.puresol.coding.java.antlr.JavaRange;
import com.puresol.coding.java.antlr.JavaParserHelper;
import com.puresol.coding.java.antlr.output.JavaLexer;
import com.puresol.coding.java.antlr.output.JavaParser;

/**
 * 
 * @author ludwig
 * 
 */
public class JavaAnalyser implements SourceCodeAnalyser {

	private static final Logger logger = Logger.getLogger(JavaAnalyser.class);

	private File file = null;
	private JavaLexerHelper helper = new JavaLexerHelper();

	public JavaAnalyser(File file) {
		this.file = file;
		analyse();
	}

	private void analyse() {
		try {
			InputStream in = new FileInputStream(file);
			JavaLexer lexer = new JavaLexer(new ANTLRInputStream(in), helper);
			CommonTokenStream cts = new CommonTokenStream(lexer);
			JavaParser parser = new JavaParser(cts);
			parser.file();
			// HalsteadMetric halstead = new
			// HalsteadMetric(helper.getOperators(),
			// helper.getOperands());
			// halstead.printOperators();
			// halstead.printOperands();
			// System.out.println(halstead.toString());
			JavaParserHelper helper = parser.getJavaParserHelper();
			JavaRange range = helper.getMethods().get(3);
			// for (JavaMethod method : helper.getMethods()) {
			System.out.println("+++++++++++++++++++++");
			System.out.println(range.toString());
			System.out.println("+++++++++++++++++++++");
			HalsteadMetric halstead = new HalsteadMetric(range.getOperators(),
					range.getOperants());
			halstead.printOperators();
			halstead.printOperands();
			System.out.println(halstead.getResultsAsString());
			System.out.println("Cyclomatic number: ");
			System.out.println(range.getCyclomaticNumber());
			// }
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (RecognitionException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		Logger.getRootLogger().setLevel(Level.TRACE);
		JavaAnalyser analyser = new JavaAnalyser(new File(
				"src/com/puresol/coding/java/JavaAnalyser.java"));
	}
}
