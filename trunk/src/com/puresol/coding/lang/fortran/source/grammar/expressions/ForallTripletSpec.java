package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R753 forall-triplet-spec is index-name = forall-limit : forall-limit [ : forall-step]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ForallTripletSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(IndexName.class);
		expectToken(Equals.class);
		expectPart(ForallLimit.class);
		expectToken(Colon.class);
		expectPart(ForallLimit.class);
		if (acceptToken(Colon.class) != null) {
			expectPart(ForallStep.class);
		}
		finish();
	}
}
