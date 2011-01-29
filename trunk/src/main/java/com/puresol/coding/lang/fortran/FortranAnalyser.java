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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.AnalyzerException;
import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.trees.TreeException;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.utils.Persistence;
import com.puresol.utils.PersistenceException;
import com.puresol.utils.StopWatch;

/**
 * This is the Fortran analyzer to scan and parse source files in Fortran source
 * code.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FortranAnalyser implements Analyzer {

	private static final long serialVersionUID = 2265150343844799735L;

	private static final Logger logger = Logger
			.getLogger(FortranAnalyser.class);

	private final File file;
	private final transient FortranGrammar grammar;
	private Date date = new Date();
	private ParserTree parserTree = null;

	public FortranAnalyser(File file) {
		super();
		this.file = file;
		grammar = FortranGrammar.getInstance();
	}

	@Override
	public void parse() throws AnalyzerException {
		try {
			TokenStream tokenStream = preConditioningAndLexing();
			StopWatch watch = new StopWatch();
			Parser parser = grammar.getParser();
			logger.debug("Starting parser...");
			watch.start();
			parserTree = parser.parse(tokenStream);
			watch.stop();
			logger.debug("done. (time: " + watch + ")");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (ParserException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (PersistenceException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		}
	}

	private TokenStream preConditioningAndLexing() throws AnalyzerException {
		try {
			FortranPreConditioner preconditioner = new FortranPreConditioner(
					file);
			StopWatch watch = new StopWatch();
			logger.debug("Start lexical scanner...");
			watch.start();
			TokenStream tokenStream = preconditioner.scan(grammar.getLexer());
			watch.stop();
			logger.debug("done. (time: " + watch + ")");
			return tokenStream;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (LexerException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (PersistenceException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		}
	}

	public Fortran getLanguage() {
		return Fortran.getInstance();
	}

	@Override
	public ParserTree getParserTree() {
		return parserTree;
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public Date getTimeStamp() {
		return date;
	}

	@Override
	public boolean persist(File file) {
		try {
			Persistence.persist(this, file);
			return true;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public List<CodeRange> getAnalyzableCodeRanges() {
		final List<CodeRange> result = new ArrayList<CodeRange>();
		result.add(new CodeRange("", CodeRangeType.FILE, parserTree));
		TreeWalker<ParserTree> walker = new TreeWalker<ParserTree>(parserTree);
		walker.walk(new TreeVisitor<ParserTree>() {

			@Override
			public WalkingAction visit(ParserTree tree) {
				try {
					if ("main-program".equals(tree.getName())) {
						String name = tree.getChild("program-stmt")
								.getChildren("NAME_LITERAL").get(1).getText();
						result.add(new CodeRange(name, CodeRangeType.PROGRAM,
								tree));
					} else if ("function-subprogram".equals(tree.getName())) {
						String name = tree.getChild("function-stmt")
								.getChildren("NAME_LITERAL").get(1).getText();
						result.add(new CodeRange(name, CodeRangeType.FUNCTION,
								tree));
					} else if ("subroutine-subprogram".equals(tree.getName())) {
						String name = tree.getChild("subroutine-stmt")
								.getChildren("NAME_LITERAL").get(1).getText();
						result.add(new CodeRange(name,
								CodeRangeType.SUBROUTINE, tree));
					} else if ("module".equals(tree.getName())) {
						String name = tree.getChild("module-stmt")
								.getChildren("NAME_LITERAL").get(1).getText();
						result.add(new CodeRange(name, CodeRangeType.MODULE,
								tree));
					} else if ("submodule".equals(tree.getName())) {
						String name = tree.getChild("submodule-stmt")
								.getChildren("NAME_LITERAL").get(1).getText();
						result.add(new CodeRange(name, CodeRangeType.MODULE,
								tree));
					}
					return WalkingAction.PROCEED;
				} catch (TreeException e) {
					logger.error(e.getMessage(), e);
					return WalkingAction.ABORT;
				}
			}

		});
		return result;
	}

}
