/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.java;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.puresol.coding.AbstractAnalyser;
import com.puresol.coding.CodeRange;
import com.puresol.coding.Language;
import com.puresol.coding.java.metrics.CodeRangeMetrics4Java;
import com.puresol.coding.java.parts.JavaFile;
import com.puresol.parser.DefaultPreConditioner;
import com.puresol.parser.Lexer;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.Parser;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaAnalyser extends AbstractAnalyser {

	private static final Logger logger = Logger.getLogger(JavaAnalyser.class);

	private Lexer lexer = null;
	private Parser parser = null;
	private int lineNumber = 0;

	public static boolean isSuitable(File file) {
		return file.getPath().endsWith(".java");
	}

	/**
	 * This is the default constructor.
	 * 
	 * @param A
	 *            file to be analysed.
	 */
	public JavaAnalyser(File projectDirectory, File file) {
		super(projectDirectory, file);
		parse();
	}

	private void parse() {
		try {
			lexer = new Lexer(new DefaultPreConditioner(new File(
					getProjectDirectory().toString() + "/"
							+ getFile().toString())).getTokenStream());
			TokenStream tokenStream = lexer.getTokenStream();
			parser = new Parser(tokenStream);
			parser.parse(JavaFile.class);
			// TODO implement code ranges!
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (NoMatchingTokenDefinitionFound e) {
			logger.error(e.getMessage(), e);
		} catch (PartDoesNotMatchException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	protected void calculate() {
		clearAllMetrics();
		for (CodeRange codeRange : getCodeRanges()) {
			addMetrics(codeRange, new CodeRangeMetrics4Java(codeRange));
		}
	}

	public Language getLanguage() {
		return Language.JAVA;
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}
}
