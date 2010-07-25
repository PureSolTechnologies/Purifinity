package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.types.DerivedTypeSpec;
import com.puresol.coding.lang.fortran.source.grammar.types.TypeSpec;
import com.puresol.coding.lang.fortran.source.keywords.ClassDefaultKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ClassIsKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PartIsKeyword;
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
		if (acceptToken(PartIsKeyword.class) != null) {
			expectToken(LParen.class);
			expectPart(TypeSpec.class);
			expectToken(RParen.class);
		} else if (acceptToken(ClassIsKeyword.class) != null) {
			expectToken(LParen.class);
			expectPart(DerivedTypeSpec.class);
			expectToken(RParen.class);
		} else if (acceptToken(ClassDefaultKeyword.class) != null) {
		} else {
			abort();
		}
		acceptPart(SelectConstructName.class);
		finish();
	}
}
