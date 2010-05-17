package com.puresol.coding.lang.fortran.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.EndKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndProgramKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ProgramKeyword;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Program extends AbstractFortranParser {

    private static final long serialVersionUID = -1171971633742387488L;

    @SuppressWarnings("unchecked")
    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	try {
	    expectToken(ProgramKeyword.class);

	    String name = getCurrentToken().getText();
	    expectToken(name);

	    // TODO read here the code...
	    skipTo(EndKeyword.class, EndProgramKeyword.class);

	    expectToken(EndKeyword.class, EndProgramKeyword.class);
	    acceptToken(name);

	    finish(name);
	} catch (EndOfTokenStreamException e) {
	    throw new PartDoesNotMatchException(this);
	}
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.PROGRAM;
    }
}
