package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.BindKeyword;
import com.puresol.coding.lang.fortran.source.keywords.CKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EnumKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R459 enum-def-stmt is ENUM, BIND(C)
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EnumDefStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(EnumKeyword.class);
		expectToken(Comma.class);
		expectToken(BindKeyword.class);
		expectToken(LParen.class);
		expectToken(CKeyword.class);
		expectToken(RParen.class);
		finish();
	}

}
