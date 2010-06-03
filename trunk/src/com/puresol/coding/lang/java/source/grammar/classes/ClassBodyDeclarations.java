package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * (classBodyDeclaration )+
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ClassBodyDeclarations extends AbstractJavaParser {

    private static final long serialVersionUID = -2656071830287957232L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectPart(ClassBodyDeclaration.class);
	while (acceptPart(ClassBodyDeclaration.class) != null) {
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
