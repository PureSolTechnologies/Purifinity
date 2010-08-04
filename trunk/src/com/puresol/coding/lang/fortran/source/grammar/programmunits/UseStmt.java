package com.puresol.coding.lang.fortran.source.grammar.programmunits;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.OnlyKeyword;
import com.puresol.coding.lang.fortran.source.keywords.UseKeyword;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1109 use-stmt is USE [ [ , module-nature ] :: ] module-name [ , rename-list ]
 * or USE [ [ , module-nature ] :: ] module-name , ONLY : [ only-list ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class UseStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(UseKeyword.class);
		if (acceptToken(Colon.class) != null) {
			expectToken(Colon.class);
		} else if (acceptToken(Comma.class) != null) {
			expectPart(ModuleNature.class);
			expectToken(Colon.class);
			expectToken(Colon.class);
		}
		expectToken(NameLiteral.class);
		if (acceptToken(Comma.class) != null) {
			if (acceptPart(RenameList.class) != null) {
			} else {
				expectToken(OnlyKeyword.class);
				expectToken(Colon.class);
				acceptPart(OnlyList.class);
			}
		}
		finish();
	}

}
