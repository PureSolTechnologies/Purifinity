/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.framework.lang.java7;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.trees.api.TreeException;
import com.puresoltechnologies.commons.trees.api.TreeVisitor;
import com.puresoltechnologies.commons.trees.api.TreeWalker;
import com.puresoltechnologies.commons.trees.api.WalkingAction;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.api.source.SourceCode;
import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.impl.lexer.Lexer;
import com.puresoltechnologies.parsers.impl.lexer.LexerException;
import com.puresoltechnologies.parsers.impl.lexer.TokenStream;
import com.puresoltechnologies.parsers.impl.parser.Parser;
import com.puresoltechnologies.parsers.impl.parser.ParserException;
import com.puresoltechnologies.parsers.impl.parser.ParserTree;
import com.puresoltechnologies.parsers.impl.ust.CompilationUnit;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzerException;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguage;
import com.puresoltechnologies.purifinity.analysis.domain.AnalyzedCode;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.framework.analysis.impl.AbstractCodeAnalyzer;
import com.puresoltechnologies.purifinity.framework.commons.utils.StopWatch;
import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammar;
import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.AnnotationTypeDeclaration;
import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.ConstructorDeclaration;
import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.EnumDeclaration;
import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.MethodDeclaration;
import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.NormalClassDeclaration;
import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.NormalInterfaceDeclaration;
import com.puresoltechnologies.purifinity.framework.lang.java7.ust.CompilationUnitCreator;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaAnalyzer extends AbstractCodeAnalyzer {

	private static final Logger logger = LoggerFactory
			.getLogger(JavaAnalyzer.class);

	private CodeAnalysis fileAnalysis;

	public JavaAnalyzer(SourceCodeLocation sourceCodeLocation) {
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
					java.getVersion(), analyzedFile,
					getAnalyzableCodeRanges(compilationUnit), compilationUnit);
		} catch (LexerException | ParserException | IOException e) {
			throw new AnalyzerException(this, e);
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

	private List<CodeRange> getAnalyzableCodeRanges(UniversalSyntaxTree ust) {
		final List<CodeRange> result = new ArrayList<CodeRange>();
		result.add(new CodeRange("", "", CodeRangeType.FILE, ust));

		TreeWalker<UniversalSyntaxTree> walker = new TreeWalker<UniversalSyntaxTree>(
				ust);
		walker.walk(new TreeVisitor<UniversalSyntaxTree>() {
			@Override
			public WalkingAction visit(UniversalSyntaxTree tree) {
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
