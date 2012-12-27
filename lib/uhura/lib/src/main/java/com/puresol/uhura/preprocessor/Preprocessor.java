package com.puresol.uhura.preprocessor;

import com.puresol.uhura.source.SourceCode;

/**
 * This is an interface for PreProcessor implementations. A PreProcessor is
 * meant to pre-process a source code to introduce more information or to
 * extract information which are not part of the source itself like control and
 * storage information.
 * 
 * A famous pre-processor is the C Preprocessor which is also implemented with
 * this interface.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Preprocessor {

    public SourceCode process(SourceCode sourceCode)
	    throws PreprocessorException;

}
