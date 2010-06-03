package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.Arguments;
import com.puresol.coding.lang.java.source.keywords.SuperKeyword;
import com.puresol.coding.lang.java.source.keywords.ThisKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.LRectBracket;
import com.puresol.coding.lang.java.source.symbols.RRectBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * selector  
 *     :   '.' IDENTIFIER
 *         (arguments
 *         )?
 *     |   '.' 'this'
 *     |   '.' 'super'
 *         superSuffix
 *     |   innerCreator
 *     |   '[' expression ']'
 *     ;
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Selector extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptToken(Dot.class) != null) {
	    if (acceptToken(Identifier.class) != null) {
		acceptPart(Arguments.class);
	    } else if (acceptToken(ThisKeyword.class) != null) {
	    } else if (acceptToken(SuperKeyword.class) != null) {
		expectPart(SuperSuffix.class);
	    } else {
		abort();
	    }
	} else if (acceptPart(InnerCreator.class) != null) {
	} else if (acceptToken(LRectBracket.class) != null) {
	    expectToken(RRectBracket.class);
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
