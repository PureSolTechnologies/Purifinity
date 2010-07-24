package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ImKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ReKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Percent;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R615 complex-part-designator is designator % RE
 * or designator % IM
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ComplexPartDesignator extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(Designator.class);
		expectToken(Percent.class);
		if (acceptToken(ReKeyword.class) == null) {
			expectToken(ImKeyword.class);
		}
		finish();
	}
}
