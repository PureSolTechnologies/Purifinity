package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.GreaterThan;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.reporting.html.css.parser.symbols.Dot;

/**
 * <pre>
 * R733 pointer-assignment-stmt is data-pointer-object [ (bounds-spec-list) ] => data-target
 * or data-pointer-object (bounds-remapping-list ) => data-target
 * or proc-pointer-object => proc-target
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PointerAssignmentStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(DataPointerObject.class) != null) {
			if (acceptToken(LParen.class) != null) {
				if (acceptPart(BoundsSpecList.class) == null) {
					expectPart(BoundsRemappingList.class);
				}
				expectToken(RParen.class);
			}
			expectToken(Equals.class);
			expectToken(GreaterThan.class);
			expectPart(DataTarget.class);
		} else {
			expectPart(ProcPointerObject);
			expectToken(Equals.class);
			expectToken(GreaterThan.class);
			expectPart(ProcTarget.class);
		}
		finish();
	}
}
