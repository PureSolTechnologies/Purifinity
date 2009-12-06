package com.puresol.coding;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.log4j.Logger;

import com.puresol.coding.antlr.ANTLRJavaHelper;
import com.puresol.coding.antlr.output.JavaAnalyseLexer;
import com.puresol.coding.antlr.output.JavaAnalyseParser;

/**
 * 
 * @author ludwig
 * 
 */
public class JavaAnalyser implements SourceCodeAnalyser {

	private static final Logger logger = Logger.getLogger(JavaAnalyser.class);

	private File file = null;
	private ANTLRJavaHelper helper = new ANTLRJavaHelper();

	public JavaAnalyser(File file) {
		this.file = file;
		analyse();
	}

	private void analyse() {
		try {
			InputStream in = new FileInputStream(file);
			JavaAnalyseLexer lexer = new JavaAnalyseLexer(new ANTLRInputStream(
					in), helper);
			CommonTokenStream cts = new CommonTokenStream(lexer);
			JavaAnalyseParser parser = new JavaAnalyseParser(cts, helper);
			parser.file();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (RecognitionException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public int getSLOCCount() {
		return helper.getSlocCount();
	}

	public String getPackageName() {
		return helper.getPackageName();
	}

	public static void main(String[] args) {
		JavaAnalyser analyser = new JavaAnalyser(new File(
				"src/com/puresol/coding/JavaAnalyser.java"));

		System.out.println("Lines " + analyser.getSLOCCount());
		System.out.println("Package " + analyser.getPackageName());
	}
}
