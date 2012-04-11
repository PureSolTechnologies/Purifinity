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
public interface FileAnalysis extends Serializable {

    /**
     * This method returns the time stamp of the analysis. This can be used for
     * validity analysis by time stamp comparison for evaluators.
     * 
     * @return
     * @throws IOException
     */
    public Date getTimeStamp() throws IOException;

    /**
     * This method returns the time effort which was needed for analysis.
     * 
     * @return Returns the time effort in milliseconds
     * @throws IOException
     */
    public long getTimeOfRun() throws IOException;

    /**
     * Returns the language of the file analysed.
     * 
     * @return The language is returned.
     * @throws IOException
     */
    public ProgrammingLanguage getLanguage() throws IOException;

    /**
     * The file which was analyzed is returned.
     * 
     * @return The analyzed file is returned.
     */
    public AnalyzedFile getAnalyzedFile();

    /**
     * This method returns the parser tree.
     * 
     * @return
     * @throws IOException
     */
    public ParserTree getParserTree() throws IOException;

    public List<CodeRange> getAnalyzableCodeRanges();
}
