package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.SelectCaseKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * select-type-construct is select-type-stmt
 * [ type-guard-stmt
 * block ] ...
 * end-select-type-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SelectTypeConstruct extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(SelectTypeStmt.class);
		while (acceptPart(TypeGuardStmt.class) != null) {
			expectPart(Block.class);
		}
		expectPart(EndSelectTypeStmt.class);
		finish();
	}
}
