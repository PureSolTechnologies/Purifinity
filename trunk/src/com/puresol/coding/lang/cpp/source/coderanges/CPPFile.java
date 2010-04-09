package com.puresol.coding.lang.cpp.source.coderanges;

import com.puresol.coding.analysis.AbstractCodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.parser.TokenStream;

public class CPPFile extends AbstractCodeRange {

    private static final long serialVersionUID = 6421501489297264897L;

    public CPPFile(String name, TokenStream tokenStream, int start, int stop) {
	super(name, tokenStream, start, stop);
    }

    @Override
    public String getLanguage() {
	return "C++";
    }

    @Override
    public CodeRangeType getType() {
	return CodeRangeType.FILE;
    }

    @Override
    public String getTypeName() {
	return getType().getIdentifier();
    }

}
