package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.types.TypeSpec;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R752 forall-header is ( [ type-spec :: ] forall-triplet-spec-list [, scalar-mask-expr] )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ForallHeader extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(LParen.class);
		if (acceptPart(TypeSpec.class) != null) {
			expectToken(Colon.class);
			expectToken(Colon.class);
		}
		expectPart(ForallTripletSpecList.class);
		if (acceptToken(Comma.class) != null) {
			expectPart(MaskExpr.class);
		}
		expectToken(RParen.class);
		finish();
	}
}
