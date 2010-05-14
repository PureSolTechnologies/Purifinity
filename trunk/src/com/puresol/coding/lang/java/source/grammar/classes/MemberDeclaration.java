package com.puresol.coding.lang.java.source.grammar.classes;

import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.interfaces.InterfaceDeclaration;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * memberDecl : fieldDeclaration | methodDeclaration | classDeclaration |
 * interfaceDeclaration ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class MemberDeclaration extends AbstractJavaParser {

    private static final long serialVersionUID = -2656071830287957232L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptPart(FieldDeclaration.class) != null) {
	} else if (acceptPart(MethodDeclaration.class) != null) {
	} else if (acceptPart(ClassDeclaration.class) != null) {
	} else if (acceptPart(InterfaceDeclaration.class) != null) {
	} else {
	    abort();
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

    public List<FieldDeclaration> getFields() {
	return getChildCodeRanges(FieldDeclaration.class);
    }
}
