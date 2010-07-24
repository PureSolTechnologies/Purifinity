package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R620 section-subscript is subscript
 * or subscript-triplet
 * or vector-subscript
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SectionSubscript extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Subscript.class) != null) {
		} else if (acceptPart(SubscriptTriplet.class) != null) {
		} else if (acceptPart(VectorSubscript.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
