package com.puresol.coding.lang.java.source.grammar.interfaces;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.ClassDeclaration;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * 
 *TODO: add predicates
 * 
 * interfaceBodyDeclaration : interfaceFieldDeclaration |
 * interfaceMethodDeclaration | interfaceDeclaration | classDeclaration | ';' ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class InterfaceBodyDeclaration extends AbstractJavaParser {

    private static final long serialVersionUID = -2656071830287957232L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptPart(InterfaceFieldDeclaration.class) != null) {
	} else if (acceptPart(InterfaceMethodDeclaration.class) != null) {
	} else if (acceptPart(InterfaceDeclaration.class) != null) {
	} else if (acceptPart(ClassDeclaration.class) != null) {
	} else if (acceptToken(Semicolon.class) != null) {
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
