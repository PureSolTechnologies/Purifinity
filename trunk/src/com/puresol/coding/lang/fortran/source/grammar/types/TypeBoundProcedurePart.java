package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ContainsKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PrivateKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R445 type-bound-procedure-part is contains-stmt
 * [ binding-private-stmt ]
 * [ type-bound-proc-binding ] ...
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TypeBoundProcedurePart extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(ContainsKeyword.class);
		acceptToken(PrivateKeyword.class);
		while (acceptPart(TypeBoundProcBinding.class) != null)
			;
		finish();
	}
}
