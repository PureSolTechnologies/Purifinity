package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.expressions.Primary;
import com.puresol.coding.lang.java.source.keywords.SuperKeyword;
import com.puresol.coding.lang.java.source.keywords.ThisKeyword;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * NOTE: the position of Identifier 'super' is set to the type args position
 * here
 * 
 * explicitConstructorInvocation : (nonWildcardTypeArguments )? ('this' |'super'
 * ) arguments ';'
 * 
 * | primary '.' (nonWildcardTypeArguments )? 'super' arguments ';' ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ExplicitConstructorInvocation extends AbstractJavaParser {

    private static final long serialVersionUID = -5105706064635403458L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	try {
	    acceptPart(NonWildTypeArguments.class);
	    if (acceptToken(SuperKeyword.class) != null) {
	    } else if (acceptToken(ThisKeyword.class) != null) {
	    } else {
		abort();
	    }
	    expectPart(Arguments.class);
	    expectToken(Semicolon.class);
	    finish();
	    return;
	} catch (PartDoesNotMatchException e) {
	    expectPart(Primary.class);
	    expectToken(Dot.class);
	    acceptPart(NonWildTypeArguments.class);
	    expectToken(SuperKeyword.class);
	    expectPart(Arguments.class);
	    expectToken(Semicolon.class);
	}
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.CONSTRUCTOR;
    }

}
