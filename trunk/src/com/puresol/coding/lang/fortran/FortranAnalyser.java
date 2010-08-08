/***************************************************************************
 *
 *   FortranAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.AbstractAnalyser;
import com.puresol.coding.analysis.AnalyserException;
import com.puresol.coding.analysis.SourceCodeLexer;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.lexer.LexerException;
import com.puresol.parser.lexer.NoMatchingTokenDefinitionFound;

public class FortranAnalyser extends AbstractAnalyser {

	private static final long serialVersionUID = 2265150343844799735L;

	private static final Logger logger = Logger
			.getLogger(FortranAnalyser.class);

	public FortranAnalyser(File file) {
		super(file);
	}

	@Override
	public void parse() throws AnalyserException {
		try {
			SourceCodeLexer lexer = new SourceCodeLexer(Fortran.getInstance(),
					new FortranPreConditioner(getFile()).getTokenStream());
			FortranParser parser = (FortranParser) createParserInstance(
					FortranParser.class, lexer.getTokenStream());
			parser.scan();
			setRootCodeRange(parser);
			return;
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (NoMatchingTokenDefinitionFound e) {
			logger.error(e.getMessage(), e);
		} catch (PartDoesNotMatchException e) {
			logger.error(e.getMessage(), e);
		} catch (LexerException e) {
			logger.error(e.getMessage(), e);
		} catch (ParserException e) {
			logger.error(e.getMessage(), e);
		}
		throw new AnalyserException(this);
	}

	@Override
	public ProgrammingLanguage getLanguage() {
		return Fortran.getInstance();
	}
}
