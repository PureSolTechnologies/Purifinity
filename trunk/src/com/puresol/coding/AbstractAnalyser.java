/***************************************************************************
 *
 *   AbstractAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

import org.antlr.runtime.Lexer;
import org.antlr.runtime.Parser;
import org.apache.log4j.Logger;

import com.puresol.exceptions.StrangeSituationException;

abstract public class AbstractAnalyser implements Analyser {

    private static final Logger logger =
	    Logger.getLogger(AbstractAnalyser.class);

    private File projectDirectory = null;
    private File file = null;

    private Hashtable<CodeRange, CodeRangeMetrics> metrics =
	    new Hashtable<CodeRange, CodeRangeMetrics>();
    private ArrayList<CodeRange> codeRanges = null;

    public AbstractAnalyser(File projectDirectory, File file) {
	this.projectDirectory = projectDirectory;
	this.file = file;
    }

    public File getProjectDirectory() {
	return projectDirectory;
    }

    public File getFile() {
	return file;
    }

    public CodeRangeMetrics getMetrics(CodeRange codeRange) {
	return metrics.get(codeRange);
    }

    protected void setCodeRanges(ArrayList<CodeRange> codeRanges) {
	this.codeRanges = codeRanges;
	calculate();
    }

    abstract protected void calculate();

    protected void clearAllMetrics() {
	metrics.clear();
    }

    protected void addMetrics(CodeRange codeRange,
	    CodeRangeMetrics codeRangeMetrics) {
	metrics.put(codeRange, codeRangeMetrics);
    }

    public ArrayList<CodeRange> getCodeRanges() {
	return codeRanges;
    }

    protected Hashtable<Integer, String> getLexerTokens(
	    Class<? extends Lexer> lexer) {
	return readTokens(lexer);
    }

    protected Hashtable<Integer, String> getParserTokens(
	    Class<? extends Parser> parser) {
	return readTokens(parser);
    }

    private Hashtable<Integer, String> readTokens(Class<?> clazz) {
	try {
	    String tokensFile =
		    "/" + clazz.getName().replaceAll("\\.", "/")
			    + ".tokens";
	    InputStream input = clazz.getResourceAsStream(tokensFile);
	    if (input == null) {
		throw new StrangeSituationException(
			"Could not find tokens file: " + tokensFile);
	    }
	    Hashtable<Integer, String> tokens =
		    new Hashtable<Integer, String>();
	    BufferedReader reader =
		    new BufferedReader(new InputStreamReader(input));
	    String line;
	    while ((line = reader.readLine()) != null) {
		String[] splits = line.trim().split("=");
		tokens.put(Integer.valueOf(splits[1]), splits[0]);
	    }
	    return tokens;
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	return null;
    }
}
