package com.puresol.coding.lang.fortran.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.fortran.source.keywords.EndProgramKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ProgramKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Program extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	processToken(ProgramKeyword.class);

	String name = getCurrentToken().getText();
	processToken(name);

	// TODO read here the code...
	this.skipTokensUntil(EndProgramKeyword.class);

	processToken(EndProgramKeyword.class);
	processToken(name);
    }
}
