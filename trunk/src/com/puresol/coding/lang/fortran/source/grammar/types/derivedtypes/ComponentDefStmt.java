package com.puresol.coding.lang.fortran.source.grammar.types.derivedtypes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.KindKeyword;
import com.puresol.coding.lang.fortran.source.keywords.LenKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R435 component-def-stmt is data-component-def-stmt
 * or proc-component-def-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ComponentDefStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(DataComponentDefStmt.class) != null) {
		} else if (acceptPart(ProcComponentDefStmt.class) != null) {
		} else {
			abort();
		}

		finish();
	}

}
