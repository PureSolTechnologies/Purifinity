package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.types.DerivedTypeSpec;
import com.puresol.coding.lang.fortran.source.grammar.types.TypeSpec;
import com.puresol.coding.lang.fortran.source.keywords.ClassKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DefaultKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IsKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PartKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R848 type-guard-stmt is TYPE IS ( type-spec ) [ select-construct-name ]
 * or CLASS IS ( derived-type-spec ) [ select-construct-name ]
 * or CLASS DEFAULT [ select-construct-name ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TypeGuardStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(PartKeyword.class) != null) {
			expectToken(IsKeyword.class);
			expectToken(LParen.class);
			expectPart(TypeSpec.class);
			expectToken(RParen.class);
		} else if (acceptToken(ClassKeyword.class) != null) {
			if (acceptToken(IsKeyword.class) != null) {
				expectToken(LParen.class);
				expectPart(DerivedTypeSpec.class);
				expectToken(RParen.class);
			} else if (acceptToken(DefaultKeyword.class) != null) {
			} else {
				abort();
			}
		}
		acceptPart(SelectConstructName.class);
		finish();
	}
}
