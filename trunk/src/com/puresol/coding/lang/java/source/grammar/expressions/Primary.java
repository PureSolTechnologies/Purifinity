package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.PrimitiveType;
import com.puresol.coding.lang.java.source.keywords.ClassKeyword;
import com.puresol.coding.lang.java.source.keywords.SuperKeyword;
import com.puresol.coding.lang.java.source.keywords.ThisKeyword;
import com.puresol.coding.lang.java.source.keywords.VoidKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * have to use scope here, parameter passing isn't well supported in antlr.
 * 
 * primary 
 *     :   parExpression            
 *     |   'this'
 *         ('.' IDENTIFIER
 *         )*
 *         (identifierSuffix
 *         )?
 *     |   IDENTIFIER
 *         ('.' IDENTIFIER
 *         )*
 *         (identifierSuffix
 *         )?
 *     |   'super'
 *         superSuffix
 *     |   literal
 *     |   creator
 *     |   primitiveType
 *         ('[' ']'
 *         )*
 *         '.' 'class'
 *     |   'void' '.' 'class'
 *     ;
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Primary extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptPart(ParExpression.class) != null) {
	} else if (acceptToken(ThisKeyword.class) != null) {
	    if (acceptToken(Dot.class) != null) {
		expectToken(Identifier.class);
	    }
	    acceptPart(IdentifierSuffix.class);
	} else if (acceptToken(Identifier.class) != null) {
	    while (acceptPart(IdentifierSuffix.class) == null) {
		if (acceptToken(Dot.class) != null) {
		    expectToken(Identifier.class);
		} else {
		    break;
		}
	    }
	} else if (acceptToken(SuperKeyword.class) != null) {
	    expectPart(SuperSuffix.class);
	} else if (acceptPart(Literal.class) != null) {
	} else if (acceptPart(Creator.class) != null) {
	} else if (acceptPart(PrimitiveType.class) != null) {
	    acceptPart(Dims.class);
	    expectToken(Dot.class);
	    expectToken(ClassKeyword.class);
	} else if (acceptToken(VoidKeyword.class) != null) {
	    expectToken(Dot.class);
	    expectToken(ClassKeyword.class);
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
