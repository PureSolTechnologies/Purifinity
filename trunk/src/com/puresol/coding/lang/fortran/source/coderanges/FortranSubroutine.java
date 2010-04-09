package com.puresol.coding.lang.fortran.source.coderanges;

import com.puresol.coding.analysis.AbstractCodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.parser.TokenStream;

public class FortranSubroutine extends AbstractCodeRange {

    private static final long serialVersionUID = 4992045789399188940L;

    public FortranSubroutine(String name, TokenStream tokenStream, int start,
	    int stop) {
	super(name, tokenStream, start, stop);
    }

    @Override
    public String getLanguage() {
	return "Fortran";
    }

    @Override
    public CodeRangeType getType() {
	return CodeRangeType.SUBROUTINE;
    }

    @Override
    public String getTypeName() {
	return getType().getIdentifier();
    }

}
