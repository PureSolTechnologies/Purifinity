package com.puresol.coding.lang.java.source.grammar.interfaces;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.InterfaceKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.At;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * annotationTypeDeclaration : modifiers '@' 'interface' IDENTIFIER
 * annotationTypeBody ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnnotationTypeDeclaration extends AbstractJavaParser {

    private static final long serialVersionUID = -1812295859556451418L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	try {
	    acceptPart(InterfaceModifiers.class);
	    expectToken(At.class);
	    expectToken(InterfaceKeyword.class);
	    String name = getCurrentToken().getText();
	    expectToken(Identifier.class);
	    expectPart(AnnotationTypeBody.class);
	    finish(name);
	} catch (EndOfTokenStreamException e) {
	    throw new PartDoesNotMatchException(this);
	}
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }
}
