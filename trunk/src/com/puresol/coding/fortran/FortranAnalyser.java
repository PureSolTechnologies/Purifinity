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

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.apache.log4j.Logger;

import com.puresol.coding.AbstractAnalyser;
import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.Language;
import com.puresol.coding.TokenContent;
import com.puresol.coding.fortran.antlr.output.FortranLexer;
import com.puresol.coding.fortran.antlr.output.FortranParser;

public class FortranAnalyser extends AbstractAnalyser {
	private static final Logger logger = Logger
			.getLogger(FortranAnalyser.class);

	private FortranLexer lexer = null;
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
			InputStream in = new FileInputStream(getProjectDirectory()
					.toString()
					+ "/" + getFile().toString());
			lexer = new FortranLexer(new ANTLRInputStream(in));
			CommonTokenStream cts = new CommonTokenStream(lexer);
			FortranParser parser = new FortranParser(getFile(), cts);
			parser.file();
			TokenStream tokenStream = parser.getTokenStream();
			ArrayList<CodeRange> codeRanges = new ArrayList<CodeRange>();
			codeRanges.add(new CodeRange(getFile(), CodeRangeType.FILE,
					getFile().getName(), tokenStream,
					new Hashtable<Integer, TokenContent>(), 0, tokenStream
							.size() - 1));
			setCodeRanges(codeRanges);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (RecognitionException e) {
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

	@Override
	public Hashtable<Integer, String> getLexerTokens() {
		return getLexerTokens(FortranLexer.class);
	}

	@Override
	public Hashtable<Integer, String> getParserTokens() {
		return getParserTokens(FortranParser.class);
	}
}
