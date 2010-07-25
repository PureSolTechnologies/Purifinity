package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.IntExpr;
import com.puresol.coding.lang.fortran.source.grammar.lexical.LabelList;
import com.puresol.coding.lang.fortran.source.keywords.GotoKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R851 goto-stmt is GO TO label
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ComputedGotoStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(GotoKeyword.class);
		expectToken(LParen.class);
		expectPart(LabelList.class);
		expectToken(RParen.class);
		acceptToken(Comma.class);
		expectPart(IntExpr.class);
		finish();
	}
}
