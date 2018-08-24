package com.puresoltechnologies.purifinity.analysis.domain;

/***************************************************************************
 *
 *   Analyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

import java.io.File;
import java.io.IOException;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCode;

/**
 * This is a interface to a analyzer. It's used to implement a language
 * independent way to access a single analyzer.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface CodeAnalyzer {

    /**
     * This method returns the source which is to be analyzed.
     * 
     * @return The source code is returned as {@link SourceCode} object.
     */
    public SourceCode getSourceCode();

    public HashId getHashId();

    /**
     * This method is called to start the actual parsing process.
     * 
     * @throws IOException
     *             is thrown in case of IO issues.
     */
    public void analyze() throws IOException;

    /**
     * This method persists the analyzer into a file specified.
     * 
     * @param file
     *            is the file to be persisted.
     * @return <code>true</code> is returned in case the file could be
     *         persisted.
     */
    public boolean persist(File file);

    /**
     * This method returns the analysis of the parsing of process.
     * 
     * @return A {@link CodeAnalysis} object is returned containing the result
     *         of the analysis.
     */
    public CodeAnalysis getAnalysis();
}
