/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.cpp;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.AbstractAnalyser;
import com.puresol.parser.DefaultPreConditioner;
import com.puresol.parser.LexerException;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CPPAnalyser extends AbstractAnalyser {

	private static final long serialVersionUID = 876077325823124163L;

	private static final Logger logger = Logger.getLogger(CPPAnalyser.class);

	/**
	 * This is the default constructor.
	 * 
	 * @param A
	 *            file to be analysed.
	 * @throws LexerException
	 */
	public CPPAnalyser(File projectDirectory, File file) {
		super(projectDirectory, file);
		parse();
	}

	private void parse() {
		try {
			DefaultPreConditioner conditioner = new DefaultPreConditioner(
					getProjectDirectory(), getFile());
			TokenStream tokenStream = conditioner.getTokenStream();
			CPPLexer lexer = new CPPLexer(tokenStream);
			tokenStream = lexer.getTokenStream();
			CPPParser parser = new CPPParser(tokenStream);
			parser.scan();
			addCodeRanges(parser.getCodeRanges());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (NoMatchingTokenDefinitionFound e) {
			logger.error(e.getMessage(), e);
		} catch (PartDoesNotMatchException e) {
			logger.error(e.getMessage(), e);
		} catch (LexerException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public ProgrammingLanguage getLanguage() {
		return CPlusPlus.getInstance();
	}
}
