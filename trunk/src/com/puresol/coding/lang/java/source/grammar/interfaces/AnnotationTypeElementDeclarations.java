package com.puresol.coding.lang.java.source.grammar.interfaces;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * (annotationTypeElementDeclaration )*
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnnotationTypeElementDeclarations extends AbstractJavaParser {

    private static final long serialVersionUID = -5845018908537488666L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectPart(AnnotationTypeElementDeclaration.class);
	while (acceptPart(AnnotationTypeElementDeclaration.class) != null)
	    ;
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
