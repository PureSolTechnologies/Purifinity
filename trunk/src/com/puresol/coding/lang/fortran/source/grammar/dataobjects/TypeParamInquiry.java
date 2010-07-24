package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Percent;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R616 type-param-inquiry is designator % type-param-name
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TypeParamInquiry extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(Designator.class);
		expectToken(Percent.class);
		expectToken(NameLiteral.class);
		finish();
	}
}
