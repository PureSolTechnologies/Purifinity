package com.puresol.coding.lang.fortran.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.cpp.source.symbols.LParen;
import com.puresol.coding.lang.cpp.source.symbols.RParen;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.EndFunctionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndKeyword;
import com.puresol.coding.lang.fortran.source.keywords.FunctionKeyword;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Function extends AbstractFortranParser {

    private static final long serialVersionUID = -3467867032565700073L;

    @SuppressWarnings("unchecked")
    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	try {
	    acceptPart(VariableType.class);
	    expectToken(FunctionKeyword.class);

	    String name = getCurrentToken().getText();
	    expectToken(name);

	    skipNested(LParen.class, RParen.class);

	    // TODO read here the code...
	    skipTo(EndKeyword.class, EndFunctionKeyword.class);

	    expectToken(EndKeyword.class, EndFunctionKeyword.class);
	    acceptToken(name);

	    finish(name);
	} catch (EndOfTokenStreamException e) {
	    throw new PartDoesNotMatchException(this);
	}
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FUNCTION;
    }
}
