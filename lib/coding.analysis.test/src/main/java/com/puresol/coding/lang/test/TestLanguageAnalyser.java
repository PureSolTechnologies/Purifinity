/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.AnalyzerException;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.coding.analysis.api.FileAnalyzer;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.lang.test.grammar.TestLanguageGrammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.LexerResult;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.source.FileSource;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.HashAlgorithm;
import com.puresol.utils.HashId;
import com.puresol.utils.StopWatch;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TestLanguageAnalyser implements FileAnalyzer {

    private static final Logger logger = LoggerFactory
	    .getLogger(TestLanguageAnalyser.class);

    private final File sourceDirectory;
    private final File file;
    private final transient TestLanguageGrammar grammar;
    private FileAnalysis fileAnalysis;

    public TestLanguageAnalyser(File sourceDirectory, File file) {
	super();
	this.sourceDirectory = sourceDirectory;
	this.file = file;
	grammar = TestLanguageGrammar.getInstance();
    }

    @Override
    public void analyze() throws AnalyzerException {
	try {
	    fileAnalysis = null;
	    Date date = new Date();
	    StopWatch watch = new StopWatch();
	    watch.start();
	    HashId hashId = FileUtilities.createHashId(new File(
		    sourceDirectory, file.getPath()), HashAlgorithm.SHA256);
	    Lexer lexer = grammar.getLexer();
	    LexerResult lexerResult = lexer.lex(new FileSource(new File(
		    sourceDirectory, file.getPath())).load(), file.toString());
	    Parser parser = grammar.getParser();
	    ParserTree parserTree = parser.parse(lexerResult);
	    watch.stop();
	    long timeEffort = Math.round(watch.getSeconds() * 1000.0);
	    TestLanguage language = TestLanguage.getInstance();
	    fileAnalysis = new FileAnalysis(date, timeEffort,
		    language.getName(), language.getVersion(),
		    new AnalyzedFile(hashId, file, date, timeEffort, language
			    .getName(), language.getVersion()),
		    parserTree, getAnalyzableCodeRanges(parserTree));

	} catch (ParserException e) {
	    logger.error(e.getMessage(), e);
	    throw new AnalyzerException(this);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    throw new AnalyzerException(this);
	} catch (LexerException e) {
	    logger.error(e.getMessage(), e);
	    throw new AnalyzerException(this);
	}
	return;
    }

    @Override
    public ProgrammingLanguage getLanguage() {
	return TestLanguage.getInstance();
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
	List<CodeRange> result = new ArrayList<CodeRange>();
	result.add(new CodeRange("", CodeRangeType.FILE, parserTree));
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
    public File getFile() {
	return file;
    }

    @Override
    public FileAnalysis getAnalysis() {
	return fileAnalysis;
    }

}
