package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.DataRef;
import com.puresol.coding.lang.fortran.source.grammar.expressions.ProcComponentRef;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Percent;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * procedure-designator is procedure-name
 * or proc-component-ref
 * or data-ref % binding-name
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProcedureDesignator extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ProcedureName.class) != null) {
		} else if (acceptPart(ProcComponentRef.class) != null) {
		} else if (acceptPart(DataRef.class) != null) {
			expectToken(Percent.class);
			expectToken(NameLiteral.class);
		} else {
			abort();
		}
		finish();
	}
}
