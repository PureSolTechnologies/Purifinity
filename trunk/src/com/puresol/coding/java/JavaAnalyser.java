package com.puresol.coding.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.apache.log4j.Logger;

import com.puresol.coding.SourceCodeAnalyser;
import com.puresol.coding.java.antlr.JavaLexerHelper;
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
			for (Object o : cts.getTokens()) {
				CommonToken token = (CommonToken) o;
				System.out.println(token.getText() + "("
						+ token.getTokenIndex() + ", " + token.getLine() + ", "
						+ token.getType() + ")");
			}
			 JavaParser parser = new JavaParser(cts);
			 parser.file();
			 TokenStream stream = parser.getTokenStream();
			 for (int index = 0; index < stream.size(); index++) {
			 Token token = stream.get(index);
			 System.out.println(token.getText());
			 }
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (RecognitionException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		JavaAnalyser analyser = new JavaAnalyser(new File(
				"src/com/puresol/coding/java/JavaAnalyser.java"));
	}
}
