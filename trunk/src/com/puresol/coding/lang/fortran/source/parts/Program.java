package com.puresol.coding.lang.fortran.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.fortran.source.keywords.EndProgramKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ProgramKeyword;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.PartDoesNotMatchException;

public class Program extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException {
	processToken(ProgramKeyword.class);

	String name = getCurrentToken().getText();
	try {
	    moveForward(1);
	} catch (EndOfTokenStreamException e) {
	    throw new PartDoesNotMatchException(this);
	}

	processToken(EndProgramKeyword.class);
	processToken(name);
    }
}
