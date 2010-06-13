package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.attributes.AccessSpec;
import com.puresol.coding.lang.fortran.source.keywords.NoPassKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PassKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PointerKeyword;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R441 proc-component-attr-spec is POINTER
 * or PASS [ (arg-name) ]
 * or NOPASS
 * or access-spec
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProcComponentAttrSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(PointerKeyword.class) != null) {
		} else if (acceptToken(PassKeyword.class) != null) {
			if (acceptToken(LParen.class) != null) {
				expectToken(NameLiteral.class);
				expectToken(RParen.class);
			}
		} else if (acceptToken(NoPassKeyword.class) != null) {
		} else if (acceptPart(AccessSpec.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
