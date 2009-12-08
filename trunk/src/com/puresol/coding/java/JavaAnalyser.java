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

import com.puresol.coding.CodeRange;
import com.puresol.coding.HalsteadMetric;
import com.puresol.coding.ParserHelper;
import com.puresol.coding.SLOCStatistics;
import com.puresol.coding.java.antlr.output.JavaLexer;
import com.puresol.coding.java.antlr.output.JavaParser;

/**
 * 
 * @author ludwig
 * 
 */
public class JavaAnalyser {

	private static final Logger logger = Logger.getLogger(JavaAnalyser.class);

	private File file = null;

	public JavaAnalyser(File file) {
		this.file = file;
		analyse();
	}

	private void analyse() {
		try {
			InputStream in = new FileInputStream(file);
			JavaLexer lexer = new JavaLexer(new ANTLRInputStream(in));
			CommonTokenStream cts = new CommonTokenStream(lexer);
			SLOCStatistics slocStat = new SLOCStatistics(cts);
			slocStat.print();
			JavaParser parser = new JavaParser(cts);
			parser.file();

			ParserHelper helper = parser.getParserHelper();
			CodeRange range = helper.getMethods().get(0);
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
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (RecognitionException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		Logger.getRootLogger().setLevel(Level.INFO);
		JavaAnalyser analyser = new JavaAnalyser(new File(
				"src/com/puresol/coding/java/JavaAnalyser.java"));
		analyser.hashCode();
	}
}
