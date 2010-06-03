package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.TypeParameter;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.GreaterThan;
import com.puresol.coding.lang.java.source.symbols.LessThan;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * typeParameters 
    :   '<'
            typeParameter
            (',' typeParameter
            )*
        '>'
    ;
 * @author Rick-Rainer Ludwig
 *
 */
public class TypeParameters extends AbstractJavaParser {

    private static final long serialVersionUID = -1812295859556451418L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(LessThan.class);
	expectPart(TypeParameter.class);
	while (acceptToken(Comma.class) != null) {
	    expectPart(TypeParameter.class);
	}
	expectToken(GreaterThan.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.CLASS;
    }
}
