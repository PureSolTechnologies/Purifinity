package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.types.DeclarationTypeSpec;
import com.puresol.coding.lang.fortran.source.keywords.ElementalKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ImpureKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ModuleKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PureKeyword;
import com.puresol.coding.lang.fortran.source.keywords.RecursiveKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1226 prefix-spec is declaration-type-spec
 * or ELEMENTAL
 * or IMPURE
 * or MODULE
 * or PURE
 * or RECURSIVE
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PrefixSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(DeclarationTypeSpec.class) != null) {
		} else if (acceptToken(ElementalKeyword.class) != null) {
		} else if (acceptToken(ImpureKeyword.class) != null) {
		} else if (acceptToken(ModuleKeyword.class) != null) {
		} else if (acceptToken(PureKeyword.class) != null) {
		} else if (acceptToken(RecursiveKeyword.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
