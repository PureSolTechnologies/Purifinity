package com.puresol.coding.lang.fortran.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.EndKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndModuleKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ModuleKeyword;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Module extends AbstractFortranParser {

    private static final long serialVersionUID = 6861863707785767250L;

    @SuppressWarnings("unchecked")
    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	try {
	    expectToken(ModuleKeyword.class);

	    String name = getCurrentToken().getText();
	    expectToken(name);

	    // TODO read here the code...
	    skipTo(EndKeyword.class, EndModuleKeyword.class);

	    expectToken(EndKeyword.class, EndModuleKeyword.class);
	    acceptToken(name);

	    finish(name);
	} catch (EndOfTokenStreamException e) {
	    throw new PartDoesNotMatchException(this);
	}
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.MODULE;
    }
}
