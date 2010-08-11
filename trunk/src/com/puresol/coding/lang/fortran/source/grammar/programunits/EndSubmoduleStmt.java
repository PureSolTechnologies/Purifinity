package com.puresol.coding.lang.fortran.source.grammar.programunits;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.EndKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SubmoduleKeyword;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1119 end-submodule-stmt is END [ SUBMODULE [ submodule-name ] ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EndSubmoduleStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(EndKeyword.class);
		if (acceptToken(SubmoduleKeyword.class) != null) {
			acceptToken(NameLiteral.class);
		}
		finish();
	}

}
