package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.LBracket;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RBracket;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R631 allocation is allocate-object [ ( allocate-shape-spec-list ) ] [ lbracket allocate-coarray-spec rbracket ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Allocation extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(AllocateObject.class);
		if (acceptToken(LParen.class) != null) {
			expectPart(AllocateShapeSpecList.class);
			expectToken(RParen.class);
		}
		if (acceptToken(LBracket.class) != null) {
			expectPart(AllocateCoarraySpec.class);
			expectToken(RBracket.class);
		}
		finish();
	}
}
