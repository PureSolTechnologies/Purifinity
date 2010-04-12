package com.puresol.coding.lang.java.source.coderanges;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.AbstractCodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.Java;
import com.puresol.parser.TokenStream;

public class JavaMethod extends AbstractCodeRange {

    private static final long serialVersionUID = 7170062053904473035L;

    public JavaMethod(String name, TokenStream tokenStream, int start, int stop) {
	super(name, tokenStream, start, stop);
    }

    @Override
    public ProgrammingLanguage getLanguage() {
	return Java.getInstance();
    }

    @Override
    public CodeRangeType getType() {
	return CodeRangeType.METHOD;
    }

    @Override
    public String getTypeName() {
	return getType().getIdentifier();
    }

}
