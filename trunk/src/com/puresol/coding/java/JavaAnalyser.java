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
import com.puresol.coding.MaintainabilityIndex;
import com.puresol.coding.McCabeMetric;
import com.puresol.coding.ParserHelper;
import com.puresol.coding.SLOCMetric;
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

	private void analyse() {
		try {
			InputStream in = new FileInputStream(file);
			JavaLexer lexer = new JavaLexer(new ANTLRInputStream(in));
			CommonTokenStream cts = new CommonTokenStream(lexer);
			JavaParser parser = new JavaParser(cts);
			parser.file();

			ParserHelper helper = parser.getParserHelper();
			for (int index = 0; index < 4; index++) {
				CodeRange range = helper.getMethods().get(index);
				System.out.println("+++++++++++++++++++++");
				System.out.println(range.toString());
				System.out.println("+++++++++++++++++++++");
				HalsteadMetric halstead = new HalsteadMetric(range);
				halstead.printOperators();
				halstead.printOperands();
				System.out.println(halstead.getResultsAsString());
				McCabeMetric mcCabe = new McCabeMetric(range);
				System.out.println("Cyclomatic number: ");
				System.out.println(mcCabe.getCyclomaticNumber());
				SLOCMetric slocStat = new SLOCMetric(range);
				slocStat.print();
				MaintainabilityIndex mi = new MaintainabilityIndex(range);
				mi.print();
				if (mi.getMI() > 85) {
					System.out.println("SUPER!!!");
				} else if (mi.getMI() > 65) {
					System.out.println("OK.");
				} else {
					System.out.println("Do it again!!!");
				}
			}
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
