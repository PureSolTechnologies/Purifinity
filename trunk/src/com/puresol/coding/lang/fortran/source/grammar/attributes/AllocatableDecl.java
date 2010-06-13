package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.LSquareBracket;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.symbols.RSquareBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R527 allocatable-decl is object-name [ ( array-spec ) ]
 * [ lbracket coarray-spec rbracket ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AllocatableDecl extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(NameLiteral.class);
		if (acceptToken(LParen.class) != null) {
			expectPart(ArraySpec.class);
			expectToken(RParen.class);
		}
		if (acceptToken(LSquareBracket.class) != null) {
			expectPart(CoarraySpec.class);
			expectToken(RSquareBracket.class);
		}
		finish();
	}

}
