package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.interfaces.Annotation;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * modifiers : ( annotation | 'public' | 'protected' | 'private' | 'static' |
 * 'abstract' | 'final' | 'native' | 'synchronized' | 'transient' | 'volatile' |
 * 'strictfp' )* ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Modifiers extends AbstractJavaParser {

    private static final long serialVersionUID = 1473518113210442270L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptPart(Annotation.class) != null) {
	} else {
	    expectOneTokenOf(com.puresol.coding.lang.java.source.tokengroups.Modifiers.DEFINITIONS);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
