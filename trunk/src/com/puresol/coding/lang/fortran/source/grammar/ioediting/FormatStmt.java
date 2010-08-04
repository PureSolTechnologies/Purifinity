package com.puresol.coding.lang.fortran.source.grammar.ioediting;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.FormatKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1001 format-stmt is FORMAT format-specification
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FormatStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(FormatKeyword.class);
		expectPart(FormatSpecification.class);
		finish();
	}

}
