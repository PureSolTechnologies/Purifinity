/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.purifinity.coding.lang.java;

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
import com.puresol.purifinity.coding.lang.api.ProgrammingLanguage;
import com.puresol.purifinity.coding.lang.java.grammar.JavaGrammar;
import com.puresol.purifinity.coding.lang.java.grammar.parts.AnnotationTypeDeclaration;
import com.puresol.purifinity.coding.lang.java.grammar.parts.ConstructorDeclaration;
import com.puresol.purifinity.coding.lang.java.grammar.parts.EnumDeclaration;
import com.puresol.purifinity.coding.lang.java.grammar.parts.MethodDeclaration;
import com.puresol.purifinity.coding.lang.java.grammar.parts.NormalClassDeclaration;
import com.puresol.purifinity.coding.lang.java.grammar.parts.NormalInterfaceDeclaration;
import com.puresol.purifinity.coding.lang.java.ust.CompilationUnitCreator;
import com.puresol.purifinity.uhura.lexer.Lexer;
import com.puresol.purifinity.uhura.lexer.LexerException;
import com.puresol.purifinity.uhura.lexer.TokenStream;
import com.puresol.purifinity.uhura.parser.Parser;
import com.puresol.purifinity.uhura.parser.ParserException;
import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.source.CodeLocation;
import com.puresol.purifinity.uhura.source.SourceCode;
import com.puresol.purifinity.uhura.ust.CompilationUnit;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaAnalyzer extends AbstractCodeAnalyzer {

	private static final Logger logger = LoggerFactory
			.getLogger(JavaAnalyzer.class);

	private CodeAnalysis fileAnalysis;

	public JavaAnalyzer(CodeLocation sourceCodeLocation) {
		super(sourceCodeLocation, JavaGrammar.getInstance());
	}

	@Override
	public void analyze() throws AnalyzerException {
		try {
			fileAnalysis = null;
			Date date = new Date();
			StopWatch watch = new StopWatch();
			watch.start();
			SourceCode sourceCode = getSource().loadSourceCode();
			Lexer lexer = getGrammar().getLexer();
			TokenStream tokenStream = lexer.lex(sourceCode);
			Parser parser = getGrammar().getParser();
			ParserTree parserTree = parser.parse(tokenStream);
			watch.stop();
			CompilationUnit compilationUnit = CompilationUnitCreator
					.create(parserTree);
			long timeEffort = watch.getMilliseconds();
			Java java = Java.getInstance();
			AnalyzedCode analyzedFile = new AnalyzedCode(
					sourceCode.getHashId(), getSource(), date, timeEffort,
					java.getName(), java.getVersion());
			fileAnalysis = new CodeAnalysis(date, timeEffort, java.getName(),
					java.getVersion(), analyzedFile, parserTree,
					this.getAnalyzableCodeRanges(parserTree), compilationUnit);
		} catch (LexerException | ParserException | IOException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		}
		return;
	}

	@Override
	public CodeAnalysis getAnalysis() {
		return fileAnalysis;
	}

	@Override
	public boolean persist(File file) {
		try {
			persist(this, file);
			return true;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	private List<CodeRange> getAnalyzableCodeRanges(ParserTree parserTree) {
		final List<CodeRange> result = new ArrayList<CodeRange>();
		result.add(new CodeRange("", "", CodeRangeType.FILE, parserTree));

		TreeWalker<ParserTree> walker = new TreeWalker<ParserTree>(parserTree);
		walker.walk(new TreeVisitor<ParserTree>() {
			@Override
			public WalkingAction visit(ParserTree tree) {
				try {
					if (NormalClassDeclaration.is(tree)) {
						result.add(new NormalClassDeclaration(tree)
								.getCodeRange());
					} else if (EnumDeclaration.is(tree)) {
						result.add(new EnumDeclaration(tree).getCodeRange());
					} else if (NormalInterfaceDeclaration.is(tree)) {
						result.add(new NormalInterfaceDeclaration(tree)
								.getCodeRange());
					} else if (AnnotationTypeDeclaration.is(tree)) {
						result.add(new AnnotationTypeDeclaration(tree)
								.getCodeRange());
					} else if (ConstructorDeclaration.is(tree)) {
						result.add(new ConstructorDeclaration(tree)
								.getCodeRange());
					} else if (MethodDeclaration.is(tree)) {
						result.add(new MethodDeclaration(tree).getCodeRange());
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

	private <T> void persist(T object, File file) throws IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new FileOutputStream(file));
		try {
			objectOutputStream.writeObject(object);
		} finally {
			objectOutputStream.close();
		}
	}

	@Override
	public ProgrammingLanguage getLanguage() {
		return Java.getInstance();
	}

}
