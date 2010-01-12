/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.java;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.AbstractAnalyser;
import com.puresol.coding.lang.Language;
import com.puresol.coding.lang.java.metrics.CodeRangeMetrics4Java;
import com.puresol.parser.DefaultPreConditioner;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaAnalyser extends AbstractAnalyser {

	private static final Logger logger = Logger.getLogger(JavaAnalyser.class);

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
			DefaultPreConditioner conditioner = new DefaultPreConditioner(
					new File(getProjectDirectory().toString() + "/"
							+ getFile().toString()));
			TokenStream tokenStream = conditioner.getTokenStream();
			JavaLexer lexer = new JavaLexer(tokenStream);
			tokenStream = lexer.getTokenStream();
			JavaParser parser = new JavaParser(tokenStream);
			parser.scan();
			setCodeRanges(parser.getCodeRanges());
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
}
