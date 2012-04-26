/***************************************************************************
 *
 *   Analyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis.api;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.puresol.uhura.parser.ParserTree;

/**
 * This is a interface to a single analysis. It's used to implement a language
 * independent way to access a single file analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileAnalysis implements Serializable, TimeAwareness {

    private static final long serialVersionUID = 4670045857614462051L;

    private final Date time;
    private final long timeOfRun;
    private final String languageName;
    private final String languageVersion;
    private final AnalyzedFile analyzedFile;
    private final ParserTree parserTree;
    private final List<CodeRange> analyzableCodeRanges;

    public FileAnalysis(Date time, long timeOfRun, String languageName,
	    String languageVersion, AnalyzedFile analyzedFile,
	    ParserTree parserTree, List<CodeRange> analyzableCodeRanges) {
	super();
	this.time = time;
	this.timeOfRun = timeOfRun;
	this.languageName = languageName;
	this.languageVersion = languageVersion;
	this.analyzedFile = analyzedFile;
	this.parserTree = parserTree;
	this.analyzableCodeRanges = analyzableCodeRanges;
    }

    /**
     * This method returns the time stamp of the analysis. This can be used for
     * validity analysis by time stamp comparison for evaluators.
     * 
     * @return
     * @throws IOException
     */
    @Override
    public Date getTime() {
	return time;
    }

    /**
     * This method returns the time effort which was needed for analysis.
     * 
     * @return Returns the time effort in milliseconds
     * @throws IOException
     */
    @Override
    public long getTimeOfRun() {
	return timeOfRun;
    }

    /**
     * Returns the language of the file analysed.
     * 
     * @return The language is returned.
     * @throws IOException
     */
    public String getLanguageName() {
	return languageName;
    }

    /**
     * Returns the language version of the analyzed file.
     * 
     * @return
     */
    public String getLanguageVersion() {
	return languageVersion;
    }

    /**
     * The file which was analyzed is returned.
     * 
     * @return The analyzed file is returned.
     */
    public AnalyzedFile getAnalyzedFile() {
	return analyzedFile;
    }

    /**
     * This method returns the parser tree.
     * 
     * @return
     * @throws IOException
     */
    public ParserTree getParserTree() {
	return parserTree;
    }

    public List<CodeRange> getAnalyzableCodeRanges() {
	return analyzableCodeRanges;
    }
}
