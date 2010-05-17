package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.interfaces.Annotation;
import com.puresol.coding.lang.java.source.keywords.FinalKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * variableModifiers : 'final' | annotation ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class VariableModifier extends AbstractJavaParser {

    private static final long serialVersionUID = 1473518113210442270L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptPart(Annotation.class) != null) {
	} else if (acceptToken(FinalKeyword.class) != null) {
	} else {
	    abort();
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
