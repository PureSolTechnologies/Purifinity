package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.CharacterKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ComplexKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DoublePrecisionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IntegerKeyword;
import com.puresol.coding.lang.fortran.source.keywords.LogicalKeyword;
import com.puresol.coding.lang.fortran.source.keywords.RealKeyword;
import com.puresol.coding.lang.fortran.source.literals.IntegerLiteral;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * char-length is ( type-param-value )
 * or int-literal-constant
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CharLength extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(LParen.class) != null) {
			expectPart(TypeParamValue.class);
			expectToken(RParen.class);
		} else {
			expectToken(IntegerLiteral.class);
		}
		finish();
	}

}
