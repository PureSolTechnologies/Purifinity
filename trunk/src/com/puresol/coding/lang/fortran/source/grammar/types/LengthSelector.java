package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.LenKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R421 length-selector is ( [ LEN = ] type-param-value )
 * or * char-length [ , ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LengthSelector extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(LParen.class) != null) {
			if (acceptToken(LenKeyword.class) != null) {
				expectToken(Equals.class);
			}
			expectPart(TypeParamValue.class);
			expectToken(RParen.class);
		} else {
			expectToken(Star.class);
			expectPart(CharLength.class);
			acceptToken(Comma.class);
		}
		finish();
	}

}
