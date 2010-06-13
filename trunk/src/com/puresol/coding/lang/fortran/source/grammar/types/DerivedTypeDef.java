package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R425 derived-type-def is derived-type-stmt
 * [ type-param-def-stmt ] ...
 * [ private-or-sequence ] ...
 * [ component-part ]
 * [ type-bound-procedure-part ]
 * end-type-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DerivedTypeDef extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(DerivedTypeStmt.class);
		while (acceptPart(TypeParamDefStmt.class) != null)
			;
		while (acceptPart(PrivateOrSequence.class) != null)
			;
		acceptPart(ComponentPart.class);
		acceptPart(TypeBoundProcedurePart.class);
		expectPart(EndTypeStmt.class);
		finish();
	}

}
