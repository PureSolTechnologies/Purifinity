package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.Arguments;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.TypeArguments;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * superSuffix  
 *     :   arguments
 *     |   '.' (typeArguments
 *         )?
 *         IDENTIFIER
 *         (arguments
 *         )?
 *     ;
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SuperSuffix extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptPart(Arguments.class) != null) {
	} else if (acceptToken(Dot.class) != null) {
	    acceptPart(TypeArguments.class);
	    expectToken(Identifier.class);
	    acceptPart(Arguments.class);
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
