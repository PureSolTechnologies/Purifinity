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

import java.io.File;
import java.io.IOException;

/**
 * This is a interface to a analyzer. It's used to implement a language
 * independent way to access a single analyzer.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface FileAnalyzer extends FileAnalysis {

    /**
     * This method is called to start the actual parsing process.
     * 
     * @throws AnalyzerException
     */
    public void analyze() throws AnalyzerException, IOException;

    /**
     * This method persists the analyzer into a file specified.
     * 
     * @param file
     * @return
     */
    boolean persist(File file);

}
