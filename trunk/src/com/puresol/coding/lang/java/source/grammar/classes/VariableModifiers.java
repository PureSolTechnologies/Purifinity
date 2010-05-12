package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class VariableModifiers extends AbstractJavaParser {

    private static final long serialVersionUID = 1473518113210442270L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectPart(VariableModifier.class);
	while (acceptPart(VariableModifier.class) != null)
	    ;
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
