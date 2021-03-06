/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.server.plugin.java7;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.misc.StopWatch;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.lexer.Lexer;
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
import com.puresoltechnologies.purifinity.server.plugin.java7.grammar.JavaGrammar;
import com.puresoltechnologies.purifinity.server.plugin.java7.grammar.parts.AnnotationTypeDeclaration;
import com.puresoltechnologies.purifinity.server.plugin.java7.grammar.parts.ConstructorDeclaration;
import com.puresoltechnologies.purifinity.server.plugin.java7.grammar.parts.EnumDeclaration;
import com.puresoltechnologies.purifinity.server.plugin.java7.grammar.parts.MethodDeclaration;
import com.puresoltechnologies.purifinity.server.plugin.java7.grammar.parts.NormalClassDeclaration;
import com.puresoltechnologies.purifinity.server.plugin.java7.grammar.parts.NormalInterfaceDeclaration;
import com.puresoltechnologies.purifinity.server.plugin.java7.ust.CompilationUnitCreator;
import com.puresoltechnologies.trees.TreeException;
import com.puresoltechnologies.trees.TreeVisitor;
import com.puresoltechnologies.trees.TreeWalker;
import com.puresoltechnologies.trees.WalkingAction;

/**
 * @author Rick-Rainer Ludwig
 */
public class JavaAnalyzer extends AbstractCodeAnalyzer {

	private static final Logger logger = LoggerFactory
			.getLogger(JavaAnalyzer.class);

	private CodeAnalysis fileAnalysis;

	public JavaAnalyzer(SourceCode sourceCode, HashId hashId) {
		super(sourceCode, hashId, JavaGrammar.getInstance());
	}

	@Override
	public void analyze() throws IOException {
		fileAnalysis = null;
		Date date = new Date();
		StopWatch watch = new StopWatch();
		watch.start();
		SourceCode sourceCode = getSourceCode();
		try {
			Lexer lexer = getGrammar().getLexer();
			TokenStream tokenStream = lexer.lex(sourceCode);
			Parser parser = getGrammar().getParser();
			ParseTreeNode parserTree = parser.parse(tokenStream);
			watch.stop();
			CompilationUnit compilationUnit = CompilationUnitCreator
					.create(parserTree);
			long timeEffort = watch.getMilliseconds();
			AnalysisInformation analyzedFile = new AnalysisInformation(
					getHashId(), date, timeEffort, true, Java.NAME,
					Java.VERSION, Java.ID, Java.PLUGIN_VERSION);
			fileAnalysis = new CodeAnalysis(date, timeEffort, Java.NAME,
					Java.VERSION, analyzedFile,
					getAnalyzableCodeRanges(compilationUnit), compilationUnit);
		} catch (LexerException | ParserException e) {
			watch.stop();
			long timeEffort = watch.getMilliseconds();
			AnalysisInformation analyzedFile = new AnalysisInformation(
					getHashId(), date, timeEffort, false, Java.NAME,
					Java.VERSION, Java.ID, Java.PLUGIN_VERSION, e.getMessage());
			fileAnalysis = new CodeAnalysis(date, timeEffort, Java.NAME,
					Java.VERSION, analyzedFile, null, null);
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

}
