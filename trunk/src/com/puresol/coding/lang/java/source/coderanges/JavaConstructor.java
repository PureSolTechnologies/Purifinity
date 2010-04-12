package com.puresol.coding.lang.java.source.coderanges;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.AbstractCodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.Java;
import com.puresol.parser.TokenStream;

public class JavaConstructor extends AbstractCodeRange {

    private static final long serialVersionUID = 8912757892231761915L;

    public JavaConstructor(String name, TokenStream tokenStream, int start,
	    int stop) {
	super(name, tokenStream, start, stop);
    }

    @Override
    public ProgrammingLanguage getLanguage() {
	return Java.getInstance();
    }

    @Override
    public CodeRangeType getType() {
	return CodeRangeType.CONSTRUCTOR;
    }

    @Override
    public String getTypeName() {
	return getType().getIdentifier();
    }

}
