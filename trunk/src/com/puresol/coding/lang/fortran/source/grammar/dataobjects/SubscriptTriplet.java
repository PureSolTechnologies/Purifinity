package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R621 subscript-triplet is [ subscript ] : [ subscript ] [ : stride ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SubscriptTriplet extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptPart(Subscript.class);
		expectToken(Colon.class);
		acceptPart(Subscript.class);
		if (acceptToken(Colon.class) != null) {
			expectPart(Stride.class);
		}
		finish();
	}
}
