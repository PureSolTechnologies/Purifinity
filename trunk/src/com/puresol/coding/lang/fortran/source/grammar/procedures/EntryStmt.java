package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.EntryKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1240 entry-stmt is ENTRY entry-name [ ( [ dummy-arg-list ] ) [ sufix ] ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EntryStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(EntryKeyword.class);
		expectPart(EntryName.class);
		if (acceptToken(LParen.class) != null) {
			acceptPart(DummyArgList.class);
			expectToken(RParen.class);
			acceptPart(Suffix.class);
		}
		finish();
	}

}
