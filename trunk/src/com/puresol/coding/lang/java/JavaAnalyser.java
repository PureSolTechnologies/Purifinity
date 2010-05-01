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

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.AbstractAnalyser;
import com.puresol.parser.LexerException;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaAnalyser extends AbstractAnalyser {

	private static final long serialVersionUID = -3601131473616977648L;

	private static final Logger logger = Logger.getLogger(JavaAnalyser.class);

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
			JavaLexer lexer = new JavaLexer(getProjectDirectory(), getFile());
			CompilationUnit parser = (CompilationUnit) createParserInstance(
					CompilationUnit.class, lexer.getTokenStream());
			parser.scan();
			setRootCodeRange(parser);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (NoMatchingTokenDefinitionFound e) {
			logger.error(e.getMessage(), e);
		} catch (PartDoesNotMatchException e) {
			logger.error(e.getMessage(), e);
		} catch (ParserException e) {
			logger.error(e.getMessage(), e);
		} catch (LexerException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public ProgrammingLanguage getLanguage() {
		return Java.getInstance();
	}
}
