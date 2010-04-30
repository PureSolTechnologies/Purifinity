package com.puresol.coding.lang.java.source.parts.varop;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Relational_8 extends AbstractJavaParser {
    // < > <= >= instanceof
    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	abort();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
