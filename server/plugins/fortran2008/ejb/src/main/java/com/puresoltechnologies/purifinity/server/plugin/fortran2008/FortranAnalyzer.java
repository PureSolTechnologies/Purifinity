/***************************************************************************
 *
 *   FortranAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.server.plugin.fortran2008;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.misc.StopWatch;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.lexer.LexerException;
import com.puresoltechnologies.parsers.lexer.TokenStream;
import com.puresoltechnologies.parsers.parser.ParseTreeNode;
import com.puresoltechnologies.parsers.parser.Parser;
import com.puresoltechnologies.parsers.parser.ParserException;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.parsers.ust.CompilationUnit;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.analysis.spi.AbstractCodeAnalyzer;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.FortranGrammar;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.ust.ProgramCreator;
import com.puresoltechnologies.trees.TreeException;
import com.puresoltechnologies.trees.TreeVisitor;
import com.puresoltechnologies.trees.TreeWalker;
import com.puresoltechnologies.trees.WalkingAction;

/**
 * This is the Fortran analyzer to scan and parse source files in Fortran source
 * code.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FortranAnalyzer extends AbstractCodeAnalyzer {

	private static final Logger logger = LoggerFactory.getLogger(FortranAnalyzer.class);

	private CodeAnalysis fileAnalysis;
	private final boolean automatedFormIdentification;
	private final Pattern[] fixedFormFilePatterns;
	private final Pattern[] freeFormFilePatterns;
	private final boolean strictFormCheck;

	public FortranAnalyzer(SourceCode sourceCode, HashId hashId, boolean automatedFormIdentification,
			Pattern[] fixedFormFilePatterns, Pattern[] freeFormFilePatterns, boolean strictFormCheck) {
		super(sourceCode, hashId, FortranGrammar.getInstance());
		this.automatedFormIdentification = automatedFormIdentification;
		this.fixedFormFilePatterns = fixedFormFilePatterns;
		this.freeFormFilePatterns = freeFormFilePatterns;
		this.strictFormCheck = strictFormCheck;
	}

	@Override
	public void analyze() throws IOException {
		fileAnalysis = null;
		Date date = new Date();
		StopWatch watch = new StopWatch();
		watch.start();
		SourceCode sourceCode = getSourceCode();
		try {
			TokenStream tokenStream = preConditioningAndLexing(sourceCode);
			Parser parser = getGrammar().getParser();
			ParseTreeNode ParseTreeNode = parser.parse(tokenStream);
			watch.stop();
			CompilationUnit program = ProgramCreator.create(ParseTreeNode);
			long timeEffort = watch.getMilliseconds();
			AnalysisInformation analyzedFile = new AnalysisInformation(getHashId(), date, timeEffort, true,
					Fortran.NAME, Fortran.VERSION, Fortran.ID, Fortran.PLUGIN_VERSION);
			fileAnalysis = new CodeAnalysis(date, timeEffort, Fortran.NAME, Fortran.VERSION, analyzedFile,
					getAnalyzableCodeRanges(program), program);
		} catch (ParserException | LexerException e) {
			watch.stop();
			long timeEffort = watch.getMilliseconds();
			AnalysisInformation analyzedFile = new AnalysisInformation(getHashId(), date, timeEffort, false,
					Fortran.NAME, Fortran.VERSION, Fortran.ID, Fortran.PLUGIN_VERSION, e.getMessage());
			fileAnalysis = new CodeAnalysis(date, timeEffort, Fortran.NAME, Fortran.VERSION, analyzedFile, null, null);
		}
	}

	private TokenStream preConditioningAndLexing(SourceCode sourceCode) throws IOException, LexerException {
		SourceForm sourceForm = SourceForm.MIXED_FORM;
		if (!automatedFormIdentification) {
			for (Pattern pattern : fixedFormFilePatterns) {
				if (pattern.matcher(sourceCode.getName()).matches()) {
					sourceForm = SourceForm.FIXED_FORM;
					break;
				}
			}
			for (Pattern pattern : freeFormFilePatterns) {
				if (pattern.matcher(sourceCode.getName()).matches()) {
					if (sourceForm != SourceForm.MIXED_FORM) {
						throw new LexerException(
								"Source code with name '' fits fixed and free form pattern. Cannot decide which rules to follow.");
					}
					sourceForm = SourceForm.FREE_FORM;
					break;
				}
			}
		}
		FortranPreConditioner preconditioner = new FortranPreConditioner(sourceCode, sourceForm, strictFormCheck);
		return preconditioner.scan(getGrammar().getLexer());
	}

	@Override
	public boolean persist(File file) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
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

	private List<CodeRange> getAnalyzableCodeRanges(UniversalSyntaxTree ParseTreeNode) {
		final List<CodeRange> result = new ArrayList<CodeRange>();
		result.add(new CodeRange("", "", CodeRangeType.FILE, ParseTreeNode));
		TreeWalker<UniversalSyntaxTree> walker = new TreeWalker<UniversalSyntaxTree>(ParseTreeNode);
		walker.walk(new TreeVisitor<UniversalSyntaxTree>() {

			@Override
			public WalkingAction visit(UniversalSyntaxTree tree) {
				try {
					if ("main-program".equals(tree.getName())) {
						String name = tree.getChild("program-stmt").getChildren("NAME_LITERAL").get(1).getContent();
						result.add(new CodeRange(name, name, CodeRangeType.PROGRAM, tree));
					} else if ("function-subprogram".equals(tree.getName())) {
						String name = tree.getChild("function-stmt").getChildren("NAME_LITERAL").get(1).getContent();
						result.add(new CodeRange(name, name, CodeRangeType.FUNCTION, tree));
					} else if ("subroutine-subprogram".equals(tree.getName())) {
						String name = tree.getChild("subroutine-stmt").getChildren("NAME_LITERAL").get(1).getContent();
						result.add(new CodeRange(name, name, CodeRangeType.SUBROUTINE, tree));
					} else if ("module".equals(tree.getName())) {
						String name = tree.getChild("module-stmt").getChildren("NAME_LITERAL").get(1).getContent();
						result.add(new CodeRange(name, name, CodeRangeType.MODULE, tree));
					} else if ("submodule".equals(tree.getName())) {
						String name = tree.getChild("submodule-stmt").getChildren("NAME_LITERAL").get(1).getContent();
						result.add(new CodeRange(name, name, CodeRangeType.MODULE, tree));
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
