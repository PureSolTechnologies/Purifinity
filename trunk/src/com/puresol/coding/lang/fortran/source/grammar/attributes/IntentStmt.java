package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.procedures.DummyArgNameList;
import com.puresol.coding.lang.fortran.source.keywords.IntentKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R546 intent-stmt is INTENT ( intent-spec ) [ :: ] dummy-arg-name-list
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IntentStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(IntentKeyword.class);
		expectToken(LParen.class);
		expectPart(IntentSpec.class);
		expectToken(RParen.class);
		if (acceptToken(Colon.class) != null) {
			expectToken(Colon.class);
		}
		expectPart(DummyArgNameList.class);
		finish();
	}
}
