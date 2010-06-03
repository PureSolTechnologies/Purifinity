package com.puresol.coding.lang.java.source.grammar.interfaces;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.expressions.ConditionalExpression;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * elementValue : conditionalExpression | annotation |
 * elementValueArrayInitializer ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ElementValue extends AbstractJavaParser {

    private static final long serialVersionUID = -2656071830287957232L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptPart(ConditionalExpression.class) != null) {
	} else if (acceptPart(Annotation.class) != null) {
	} else if (acceptPart(ElementValueArrayInitializer.class) != null) {
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
