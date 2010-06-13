package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.symbols.RSquareBracket;
import com.puresol.coding.lang.fortran.source.symbols.Slash;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R468 array-constructor is (/ ac-spec /)
 * or lbracket ac-spec rbracket
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ArrayConstructor extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(LParen.class) != null) {
			expectToken(Slash.class);
			expectPart(AcSpec.class);
			expectToken(Slash.class);
			expectToken(RParen.class);
		} else if (acceptToken(RSquareBracket.class) != null) {
			expectPart(AcSpec.class);
			expectToken(RSquareBracket.class);
		} else {
			abort();
		}
		finish();
	}

}
