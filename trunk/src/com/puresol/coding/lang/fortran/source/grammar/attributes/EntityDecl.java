package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.types.CharLength;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.LBracket;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.symbols.RBracket;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R503 entity-decl is object-name [( array-spec )]
 * [ lbracket coarray-spec rbracket ]
 * [ * char-length ] [ initialization ]
 * or function-name [ * char-length ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EntityDecl extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(NameLiteral.class);
		if (acceptToken(Star.class) != null) {
			expectPart(CharLength.class);
		} else {
			if (acceptToken(LParen.class) != null) {
				expectPart(ArraySpec.class);
				expectToken(RParen.class);
			}
			if (acceptToken(LBracket.class) != null) {
				expectPart(CoarraySpec.class);
				expectToken(RBracket.class);
			}
			if (acceptToken(Star.class) != null) {
				expectPart(CharLength.class);
			}
			acceptPart(Initialization.class);
		}
		finish();
	}
}
