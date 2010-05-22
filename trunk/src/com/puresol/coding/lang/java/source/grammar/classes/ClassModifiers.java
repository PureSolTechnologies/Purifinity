package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * (classModifier)+
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ClassModifiers extends AbstractJavaParser {

    private static final long serialVersionUID = -5845018908537488666L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectPart(ClassModifier.class);
	while (acceptPart(ClassModifier.class) != null) {
	}
	finish();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
