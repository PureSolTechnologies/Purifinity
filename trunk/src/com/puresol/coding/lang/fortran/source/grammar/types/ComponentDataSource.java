package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R457 component-data-source is expr
 * or data-target
 * or proc-target
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ComponentDataSource extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Expr.class) != null) {
		} else if (acceptPart(DataTarget.class) != null) {
		} else if (acceptPart(ProcTarget.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
