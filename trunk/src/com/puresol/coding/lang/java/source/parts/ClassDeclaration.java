package com.puresol.coding.lang.java.source.parts;

import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.ClassKeyword;
import com.puresol.coding.lang.java.source.keywords.ExtendsKeyword;
import com.puresol.coding.lang.java.source.keywords.ImplementsKeyword;
import com.puresol.coding.lang.java.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.coding.langelements.ClassLanguageElement;
import com.puresol.coding.langelements.VariableLanguageElement;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ClassDeclaration extends AbstractJavaParser implements
	ClassLanguageElement {

    private static final long serialVersionUID = -1812295859556451418L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	while (acceptPart(Annotation.class) != null)
	    ;
	expectPart(ClassModifiers.class);
	expectToken(ClassKeyword.class);
	String name = getCurrentToken().getText();
	expectToken(IdLiteral.class);
	acceptPart(Generic.class);
	if (acceptToken(ExtendsKeyword.class) != null) {
	    expectToken(IdLiteral.class);
	    acceptPart(Generic.class);
	}
	if (acceptToken(ImplementsKeyword.class) != null) {
	    expectToken(IdLiteral.class);
	    acceptPart(Generic.class);
	    while (acceptToken(Comma.class) != null) {
		expectToken(IdLiteral.class);
		acceptPart(Generic.class);
	    }
	}
	expectPart(ClassBody.class);

	finish(name);
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.CLASS;
    }

    @Override
    public List<VariableLanguageElement> getFields() {
	return getChildCodeRanges(ClassBody.class).get(0).getFields();
    }
}
