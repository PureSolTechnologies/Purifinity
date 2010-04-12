package com.puresol.coding.lang.java.source.coderanges;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.AbstractCodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.Java;
import com.puresol.parser.TokenStream;

public class JavaInterface extends AbstractCodeRange {

    private static final long serialVersionUID = -3713442595720599036L;

    public JavaInterface(String name, TokenStream tokenStream, int start,
	    int stop) {
	super(name, tokenStream, start, stop);
    }

    @Override
    public ProgrammingLanguage getLanguage() {
	return Java.getInstance();
    }

    @Override
    public CodeRangeType getType() {
	return CodeRangeType.INTERFACE;
    }

    @Override
    public String getTypeName() {
	return getType().getIdentifier();
    }

}
