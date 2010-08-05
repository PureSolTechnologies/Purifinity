package com.puresol.coding.lang.fortran.source.grammar.lexical;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.AddOp;
import com.puresol.coding.lang.fortran.source.grammar.expressions.AndOp;
import com.puresol.coding.lang.fortran.source.grammar.expressions.ConcatOp;
import com.puresol.coding.lang.fortran.source.grammar.expressions.EquivOp;
import com.puresol.coding.lang.fortran.source.grammar.expressions.MultOp;
import com.puresol.coding.lang.fortran.source.grammar.expressions.NotOp;
import com.puresol.coding.lang.fortran.source.grammar.expressions.OrOp;
import com.puresol.coding.lang.fortran.source.grammar.expressions.PowerOp;
import com.puresol.coding.lang.fortran.source.grammar.expressions.RelOp;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R309 intrinsic-operator is power-op
 * or mult-op
 * or add-op
 * or concat-op
 * or rel-op
 * or not-op
 * or and-op
 * or or-op
 * or equiv-op
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IntrinsicOperator extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(PowerOp.class) != null) {
		} else if (acceptPart(MultOp.class) != null) {
		} else if (acceptPart(AddOp.class) != null) {
		} else if (acceptPart(ConcatOp.class) != null) {
		} else if (acceptPart(RelOp.class) != null) {
		} else if (acceptPart(NotOp.class) != null) {
		} else if (acceptPart(AndOp.class) != null) {
		} else if (acceptPart(OrOp.class) != null) {
		} else if (acceptPart(EquivOp.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
