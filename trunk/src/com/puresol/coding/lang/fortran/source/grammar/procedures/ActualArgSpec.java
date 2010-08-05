package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.DataRef;
import com.puresol.coding.lang.fortran.source.grammar.expressions.ProcComponentRef;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.Percent;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1222 actual-arg-spec is [ keyword = ] actual-arg
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ActualArgSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(NameLiteral.class) != null) {
			expectToken(Equals.class);
		}
		expectPart(ActualArg.class);
		finish();
	}
}
