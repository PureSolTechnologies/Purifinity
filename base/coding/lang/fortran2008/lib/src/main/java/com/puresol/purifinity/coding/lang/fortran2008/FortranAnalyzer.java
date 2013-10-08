/***************************************************************************
 *
 *   FortranAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.purifinity.coding.lang.fortran2008;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.commons.trees.TreeException;
import com.puresol.commons.trees.TreeVisitor;
import com.puresol.commons.trees.TreeWalker;
import com.puresol.commons.trees.WalkingAction;
import com.puresol.commons.utils.StopWatch;
import com.puresol.purifinity.coding.analysis.api.AbstractCodeAnalyzer;
import com.puresol.purifinity.coding.analysis.api.AnalyzedCode;
import com.puresol.purifinity.coding.analysis.api.AnalyzerException;
import com.puresol.purifinity.coding.analysis.api.CodeAnalysis;
import com.puresol.purifinity.coding.analysis.api.CodeRange;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.lang.fortran2008.grammar.FortranGrammar;
import com.puresol.purifinity.coding.lang.fortran2008.ust.ProgramCreator;
import com.puresol.purifinity.uhura.lexer.LexerException;
import com.puresol.purifinity.uhura.lexer.TokenStream;
import com.puresol.purifinity.uhura.parser.Parser;
import com.puresol.purifinity.uhura.parser.ParserException;
import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.source.CodeLocation;
import com.puresol.purifinity.uhura.source.SourceCode;
import com.puresol.purifinity.uhura.ust.CompilationUnit;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;

/**
 * This is the Fortran analyzer to scan and parse source files in Fortran source
 * code.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FortranAnalyzer extends AbstractCodeAnalyzer {

	private static final Logger logger = LoggerFactory
			.getLogger(FortranAnalyzer.class);

	private CodeAnalysis fileAnalysis;

	public FortranAnalyzer(CodeLocation sourceCodeLocation) {
		super(sourceCodeLocation, FortranGrammar.getInstance());
	}

	@Override
	public void analyze() throws AnalyzerException {
		try {
			fileAnalysis = null;
			Date date = new Date();
			StopWatch watch = new StopWatch();
			watch.start();
			SourceCode sourceCode = getSource().loadSourceCode();
			TokenStream tokenStream = preConditioningAndLexing(sourceCode);
			Parser parser = getGrammar().getParser();
			ParserTree parserTree = parser.parse(tokenStream);
			watch.stop();
			CompilationUnit program = ProgramCreator.create(parserTree);
			long timeEffort = Math.round(watch.getSeconds() * 1000.0);
			Fortran fortran = Fortran.getInstance();
			AnalyzedCode analyzedFile = new AnalyzedCode(
					sourceCode.getHashId(), getSource(), date, timeEffort,
					fortran.getName(), fortran.getVersion());
			fileAnalysis = new CodeAnalysis(date, timeEffort,
					fortran.getName(), fortran.getVersion(), analyzedFile,
					getAnalyzableCodeRanges(program), program);
		} catch (ParserException | IOException e) {
			throw new AnalyzerException(this, e);
		}
	}

	private TokenStream preConditioningAndLexing(SourceCode sourceCode)
			throws AnalyzerException {
		try {
			FortranPreConditioner preconditioner = new FortranPreConditioner(
					sourceCode);
			return preconditioner.scan(getGrammar().getLexer());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (LexerException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		}
	}

	@Override
	public Fortran getLanguage() {
		return Fortran.getInstance();
	}

	@Override
	public boolean persist(File file) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new FileOutputStream(file));
			try {
				objectOutputStream.writeObject(this);
			} finally {
				objectOutputStream.close();
			}
			return true;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	private List<CodeRange> getAnalyzableCodeRanges(
			UniversalSyntaxTree parserTree) {
		final List<CodeRange> result = new ArrayList<CodeRange>();
		result.add(new CodeRange("", "", CodeRangeType.FILE, parserTree));
		TreeWalker<UniversalSyntaxTree> walker = new TreeWalker<UniversalSyntaxTree>(
				parserTree);
		walker.walk(new TreeVisitor<UniversalSyntaxTree>() {

			@Override
			public WalkingAction visit(UniversalSyntaxTree tree) {
				try {
					if ("main-program".equals(tree.getName())) {
						String name = tree.getChild("program-stmt")
								.getChildren("NAME_LITERAL").get(1)
								.getContent();
						result.add(new CodeRange(name, name,
								CodeRangeType.PROGRAM, tree));
					} else if ("function-subprogram".equals(tree.getName())) {
						String name = tree.getChild("function-stmt")
								.getChildren("NAME_LITERAL").get(1)
								.getContent();
						result.add(new CodeRange(name, name,
								CodeRangeType.FUNCTION, tree));
					} else if ("subroutine-subprogram".equals(tree.getName())) {
						String name = tree.getChild("subroutine-stmt")
								.getChildren("NAME_LITERAL").get(1)
								.getContent();
						result.add(new CodeRange(name, name,
								CodeRangeType.SUBROUTINE, tree));
					} else if ("module".equals(tree.getName())) {
						String name = tree.getChild("module-stmt")
								.getChildren("NAME_LITERAL").get(1)
								.getContent();
						result.add(new CodeRange(name, name,
								CodeRangeType.MODULE, tree));
					} else if ("submodule".equals(tree.getName())) {
						String name = tree.getChild("submodule-stmt")
								.getChildren("NAME_LITERAL").get(1)
								.getContent();
						result.add(new CodeRange(name, name,
								CodeRangeType.MODULE, tree));
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

	@Override
	public CodeAnalysis getAnalysis() {
		return fileAnalysis;
	}

}
