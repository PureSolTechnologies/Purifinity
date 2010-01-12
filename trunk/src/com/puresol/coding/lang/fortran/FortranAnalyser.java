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

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.AbstractAnalyser;
import com.puresol.coding.lang.Language;
import com.puresol.coding.lang.fortran.metrics.CodeRangeMetrics4Fortran;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class FortranAnalyser extends AbstractAnalyser {
	private static final Logger logger = Logger
			.getLogger(FortranAnalyser.class);

	public static boolean isSuitable(File file) {
		return (file.getPath().endsWith(".f")
				|| file.getPath().endsWith(".f77")
				|| file.getPath().endsWith(".f90") || file.getPath().endsWith(
				".f95"));
	}

	/**
	 * This is the default constructor.
	 * 
	 * @param A
	 *            file to be analysed.
	 */
	public FortranAnalyser(File projectDirectory, File file) {
		super(projectDirectory, file);
		parse();
	}

	private void parse() {
		try {
			FortranLexer lexer = new FortranLexer(new FortranPreConditioner(
					new File(getProjectDirectory().toString() + "/"
							+ getFile().toString())).getTokenStream());
			TokenStream tokenStream = lexer.getTokenStream();
			FortranParser parser = new FortranParser(tokenStream);
			parser.scan();
			setCodeRanges(parser.getCodeRanges());
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
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
			addMetrics(codeRange, new CodeRangeMetrics4Fortran(codeRange));
		}
	}

	public Language getLanguage() {
		return Language.FORTRAN;
	}
}
