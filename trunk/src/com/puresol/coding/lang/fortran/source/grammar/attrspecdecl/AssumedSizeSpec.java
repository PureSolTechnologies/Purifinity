package com.puresol.coding.lang.fortran.source.grammar.attrspecdecl;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R521 assumed-size-spec is [ explicit-shape-spec , ]... [ lower-bound : ] *
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AssumedSizeSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		while (acceptPart(ExplicitShapeSpec.class) != null) {
			expectToken(Comma.class);
		}
		if (acceptPart(SpecificationExpr.class) != null) {
			expectToken(Colon.class);
		}
		expectToken(Star.class);
		finish();
	}
}
