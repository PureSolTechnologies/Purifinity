package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.ForallHeader;
import com.puresol.coding.lang.fortran.source.grammar.expressions.IntExpr;
import com.puresol.coding.lang.fortran.source.grammar.expressions.LogicalExpr;
import com.puresol.coding.lang.fortran.source.keywords.ConcurrentKeyword;
import com.puresol.coding.lang.fortran.source.keywords.WhileKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R818 loop-control is [ , ] do-variable = scalar-int-expr , scalar-int-expr
 * [ , scalar-int-expr ]
 * or [ , ] WHILE ( scalar-logical-expr )
 * or [ , ] CONCURRENT forall-header
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LoopControl extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptToken(Comma.class);
		if (acceptPart(DoVariable.class) != null) {
			expectToken(Equals.class);
			expectPart(IntExpr.class);
			expectToken(Comma.class);
			expectPart(IntExpr.class);
			if (acceptToken(Comma.class) != null) {
				expectPart(IntExpr.class);
			}
		} else if (acceptToken(WhileKeyword.class) != null) {
			expectToken(LParen.class);
			expectPart(LogicalExpr.class);
			expectToken(RParen.class);
		} else if (acceptToken(ConcurrentKeyword.class) != null) {
			expectPart(ForallHeader.class);
		} else {
			abort();
		}
		finish();
	}
}
