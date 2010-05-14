package com.puresol.coding.lang.java.source.grammar.types_values_variables;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.ExtendsKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * typeParameter : IDENTIFIER ('extends' typeBound )? ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TypeParameter extends AbstractJavaParser {

    private static final long serialVersionUID = 7523184950953085838L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(Identifier.class);
	if (acceptToken(ExtendsKeyword.class) != null) {
	    expectPart(TypeBound.class);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

    public String getVariableTypeName() {
	return getContinuousText();
    }
}
