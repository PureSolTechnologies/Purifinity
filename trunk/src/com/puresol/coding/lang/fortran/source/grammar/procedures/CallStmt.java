package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.CallKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ModuleKeyword;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1220 call-stmt is CALL procedure-designator [ ( [ actual-arg-spec-list ] ) ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CallStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(CallKeyword.class);
		if (acceptToken(LParen.class) != null) {
			acceptPart(ActualArgSpecList.class);
			expectToken(RParen.class);
		}
		finish();
	}

}
