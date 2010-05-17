package com.puresol.coding.lang.java;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.source.grammar.CompilationUnit;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * This class is the root parser for each file. In the Specifications this is
 * called CompilationUnit.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaParser extends CompilationUnit {

    private static final long serialVersionUID = -5271390812159304045L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	try {
	    moveToNextVisible(0);
	} catch (EndOfTokenStreamException e) {
	    /*
	     * this may happen if there is an empty file...
	     */
	    return;
	}
	super.scan();
	try {
	    moveToNextVisible(getCurrentPosition());
	    /*
	     * There are some things left in files which do not fit there...
	     */
	    throw new PartDoesNotMatchException(this);
	} catch (EndOfTokenStreamException e) {
	    /*
	     * This is expected, because we should at the end of the file... ;-)
	     */
	}
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FILE;
    }
}
