package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.attrspecdecl.AccessSpec;
import com.puresol.coding.lang.fortran.source.keywords.AbstractKeyword;
import com.puresol.coding.lang.fortran.source.keywords.BindKeyword;
import com.puresol.coding.lang.fortran.source.keywords.CKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ExtendsKeyword;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R427 type-attr-spec is ABSTRACT
 * or access-spec
 * or BIND (C)
 * or EXTENDS ( parent-type-name )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TypeAttrSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(AbstractKeyword.class) != null) {
		} else if (acceptPart(AccessSpec.class) != null) {
		} else if (acceptToken(BindKeyword.class) != null) {
			expectToken(LParen.class);
			expectToken(CKeyword.class);
			expectToken(RParen.class);
		} else if (acceptToken(ExtendsKeyword.class) != null) {
			expectToken(LParen.class);
			expectToken(NameLiteral.class);
			expectToken(RParen.class);
		} else {
			abort();
		}
		finish();
	}

}
