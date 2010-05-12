package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.interfaces.NormalAnnotation;
import com.puresol.coding.lang.java.source.keywords.EnumKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.GreaterThan;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.LessThan;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class EnumDeclaration extends AbstractJavaParser {

    private static final long serialVersionUID = -5500980743550485400L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	while (acceptPart(NormalAnnotation.class) != null)
	    ;
	expectPart(ClassModifiers.class);
	expectToken(EnumKeyword.class);
	if (isToken(LessThan.class)) {
	    expectToken(LessThan.class);
	    expectToken(Identifier.class);
	    while (acceptToken(Comma.class) != null) {
		expectToken(LessThan.class);
	    }
	    expectToken(GreaterThan.class);
	}
	String name = getCurrentToken().getText();

	expectToken(Identifier.class);
	skipNested(LCurlyBracket.class, RCurlyBracket.class);

	finish(name);
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.ENUMERATION;
    }
}
