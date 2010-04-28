package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.LRectBracket;
import com.puresol.coding.lang.java.source.symbols.RRectBracket;
import com.puresol.coding.lang.java.source.tokengroups.Primitives;
import com.puresol.coding.langelements.VariableTypeLanguageElement;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class VariableType extends AbstractJavaParser implements
	VariableTypeLanguageElement {

    private static final long serialVersionUID = 7523184950953085838L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (isCurrentOneOf(Primitives.DEFINITIONS)) {
	    expectOneOf(Primitives.DEFINITIONS);
	} else {
	    expectPart(ObjectType.class);
	}
	acceptPart(Generic.class);
	if (isToken(LRectBracket.class)) {
	    expectToken(LRectBracket.class);
	    expectToken(RRectBracket.class);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

    @Override
    public String getVariableTypeName() {
	return getContinuousText();
    }
}
