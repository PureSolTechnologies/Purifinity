package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.Variable;
import com.puresol.coding.lang.fortran.source.grammar.expressions.Expr;
import com.puresol.coding.lang.fortran.source.grammar.expressions.ProcComponentRef;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1223 actual-arg is expr
 * or variable
 * or procedure-name
 * or proc-component-ref
 * or alt-return-spec
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ActualArg extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Expr.class) != null) {
		} else if (acceptPart(Variable.class) != null) {
		} else if (acceptPart(ProcedureName.class) != null) {
		} else if (acceptPart(ProcComponentRef.class) != null) {
		} else if (acceptPart(AltReturnSpec.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
