package com.puresol.coding.lang.java.source.coderanges;

import com.puresol.coding.analysis.AbstractCodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.parser.TokenStream;

public class JavaClass extends AbstractCodeRange {

    private static final long serialVersionUID = 2738259501511201827L;

    public JavaClass(String name, TokenStream tokenStream, int start, int stop) {
	super(name, tokenStream, start, stop);
    }

    @Override
    public String getLanguage() {
	return "Java";
    }

    @Override
    public CodeRangeType getType() {
	return CodeRangeType.CLASS;
    }

    @Override
    public String getTypeName() {
	return getType().getIdentifier();
    }

}
