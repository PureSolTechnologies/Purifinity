/***************************************************************************
 *
 *   FortranAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.fortran;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.puresol.coding.AbstractAnalyser;
import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.Language;
import com.puresol.coding.TokenContent;
import com.puresol.parser.Lexer;
import com.puresol.parser.NoMatchingTokenDefintionFound;
import com.puresol.parser.Parser;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class FortranAnalyser extends AbstractAnalyser {
	private static final Logger logger = Logger
			.getLogger(FortranAnalyser.class);

	private Lexer lexer = null;
	private int lineNumber = 0;

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
			lexer = new Lexer(new FortranPreConditioner(new File(
					getProjectDirectory().toString() + "/"
							+ getFile().toString())).getTokenStream());
			TokenStream tokenStream = lexer.getTokenStream();
			Parser parser = new Parser(tokenStream);
			parser.parse(FortranFile.class);
			ArrayList<CodeRange> codeRanges = new ArrayList<CodeRange>();
			codeRanges.add(new CodeRange(getFile(), CodeRangeType.FILE,
					getFile().getName(), tokenStream,
					new Hashtable<Integer, TokenContent>(), 0, tokenStream
							.getSize() - 1));
			setCodeRanges(codeRanges);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (NoMatchingTokenDefintionFound e) {
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

	@Override
	public int getLineNumber() {
		return lineNumber;
	}
}
