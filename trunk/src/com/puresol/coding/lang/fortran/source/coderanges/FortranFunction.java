package com.puresol.coding.lang.fortran.source.coderanges;

import com.puresol.coding.analysis.AbstractCodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.parser.TokenStream;

public class FortranFunction extends AbstractCodeRange {

    private static final long serialVersionUID = -214707823223597271L;

    public FortranFunction(String name, TokenStream tokenStream, int start,
	    int stop) {
	super(name, tokenStream, start, stop);
    }

    @Override
    public String getLanguage() {
	return "Fortran";
    }

    @Override
    public CodeRangeType getType() {
	return CodeRangeType.FUNCTION;
    }

    @Override
    public String getTypeName() {
	return getType().getIdentifier();
    }

}
