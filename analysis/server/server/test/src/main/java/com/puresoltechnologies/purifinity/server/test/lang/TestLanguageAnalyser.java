/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.server.test.lang;

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
import com.puresoltechnologies.purifinity.server.test.lang.grammar.TestLanguageGrammar;
import com.puresoltechnologies.versioning.Version;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TestLanguageAnalyser extends AbstractCodeAnalyzer {

    private static final Logger logger = LoggerFactory.getLogger(TestLanguageAnalyser.class);

    private CodeAnalysis fileAnalysis;

    public TestLanguageAnalyser(SourceCode sourceCode, HashId hashId) {
	super(sourceCode, hashId, TestLanguageGrammar.getInstance());
    }

    @Override
    public void analyze() throws IOException {
	fileAnalysis = null;
	Date date = new Date();
	StopWatch watch = new StopWatch();
	watch.start();
	SourceCode sourceCode = getSourceCode();
	TestLanguage language = TestLanguage.getInstance();
	try {
	    Lexer lexer = getGrammar().getLexer();
	    TokenStream tokenStream = lexer.lex(sourceCode);
	    Parser parser = getGrammar().getParser();
	    parser.parse(tokenStream);
	    watch.stop();
	    CompilationUnit compilationUnit = null; // TODO
	    long timeEffort = watch.getMilliseconds();
	    fileAnalysis = new CodeAnalysis(date, timeEffort, language.getName(), language.getVersion(),
		    new AnalysisInformation(getHashId(), date, timeEffort, true, language.getName(),
			    language.getVersion(), "analyzerId", Version.valueOf("1.0.0")),
		    getAnalyzableCodeRanges(compilationUnit), compilationUnit);
	} catch (LexerException | ParserException e) {
	    long timeEffort = watch.getMilliseconds();
	    fileAnalysis = new CodeAnalysis(date, timeEffort, language.getName(),
		    language.getVersion(), new AnalysisInformation(getHashId(), date, timeEffort, false,
			    language.getName(), language.getVersion(), "analyzerId", Version.valueOf("1.0.0")),
		    null, null);
	}
	return;
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
	List<CodeRange> result = new ArrayList<CodeRange>();
	result.add(new CodeRange("", "", CodeRangeType.FILE, ust));
	return result;
    }

    private <T> void persist(T object, File file) throws IOException {
	ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
	try {
	    objectOutputStream.writeObject(object);
	} finally {
	    objectOutputStream.close();
	}
    }

    @Override
    public CodeAnalysis getAnalysis() {
	return fileAnalysis;
    }

}
