package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.attributes.CoarraySpec;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.LSquareBracket;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.symbols.RSquareBracket;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R438 component-decl is component-name [ ( component-array-spec ) ]
 * [ lbracket coarray-spec rbracket ]
 * [ * char-length ] [ component-initialization ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ComponentDecl extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(NameLiteral.class);
		if (acceptToken(LParen.class) != null) {
			expectPart(ComponentArraySpec.class);
			expectToken(RParen.class);
		}
		if (acceptToken(LSquareBracket.class) != null) {
			expectPart(CoarraySpec.class);
			expectToken(RSquareBracket.class);
		}
		if (acceptToken(Star.class) != null) {
			expectPart(CharLength.class);
		}
		acceptPart(ComponentInitialization.class);
		finish();
	}
}
