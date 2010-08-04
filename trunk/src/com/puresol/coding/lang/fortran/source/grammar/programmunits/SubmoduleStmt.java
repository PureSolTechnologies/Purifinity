package com.puresol.coding.lang.fortran.source.grammar.programmunits;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.SubmoduleKeyword;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1117 submodule-stmt is SUBMODULE ( parent-identier ) submodule-name
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SubmoduleStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(SubmoduleKeyword.class);
		expectToken(LParen.class);
		expectToken(ParentIdentifier.class);
		expectToken(RParen.class);
		expectToken(NameLiteral.class);
		finish();
	}
}
