package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.coderanges.JavaClass;
import com.puresol.coding.lang.java.source.keywords.ClassKeyword;
import com.puresol.coding.lang.java.source.keywords.ExtendsKeyword;
import com.puresol.coding.lang.java.source.keywords.ImplementsKeyword;
import com.puresol.coding.lang.java.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ClassDeclaration extends AbstractJavaParser {

    private static final long serialVersionUID = -1812295859556451418L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	while (acceptPart(Annotation.class))
	    ;
	expectPart(ClassModifiers.class);
	expectToken(ClassKeyword.class);
	String name = getCurrentToken().getText();
	expectToken(IdLiteral.class);
	acceptPart(Generic.class);
	if (acceptToken(ExtendsKeyword.class)) {
	    expectToken(IdLiteral.class);
	    acceptPart(Generic.class);
	}
	if (acceptToken(ImplementsKeyword.class)) {
	    expectToken(IdLiteral.class);
	    acceptPart(Generic.class);
	    while (acceptToken(Comma.class)) {
		expectToken(IdLiteral.class);
		acceptPart(Generic.class);
	    }
	}
	expectPart(ClassBody.class);
	int startPosition = getStartPositionWithLeadingHidden();
	int stopPosition = getPositionOfLastVisible();
	stopPosition = this.getPositionOfNextLineBreak(stopPosition);

	addCodeRange(new JavaClass(name, getTokenStream(), startPosition,
		stopPosition));
    }
}
