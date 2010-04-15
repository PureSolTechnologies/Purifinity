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
import com.puresol.parser.LexerException;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

public class FortranAnalyser extends AbstractAnalyser {

	private static final long serialVersionUID = 2265150343844799735L;

	private static final Logger logger = Logger
			.getLogger(FortranAnalyser.class);

	public FortranAnalyser(File projectDirectory, File file) {
		super(projectDirectory, file);
		parse();
	}

	private void parse() {
		try {
			FortranLexer lexer = new FortranLexer(new FortranPreConditioner(
					getProjectDirectory(), getFile()).getTokenStream());
			TokenStream tokenStream = lexer.getTokenStream();
			FortranParser parser = Instances
					.createInstance(FortranParser.class);
			parser.setTokenStream(tokenStream);
			parser.setSymbolTable(getSymbols());
			parser.scan();
			addCodeRanges(parser.getCodeRanges());
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
		} catch (ClassInstantiationException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public ProgrammingLanguage getLanguage() {
		return Fortran.getInstance();
	}
}
