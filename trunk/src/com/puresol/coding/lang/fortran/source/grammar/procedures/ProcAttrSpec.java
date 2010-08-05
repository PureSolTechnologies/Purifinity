package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.attributes.AccessSpec;
import com.puresol.coding.lang.fortran.source.grammar.attributes.IntentSpec;
import com.puresol.coding.lang.fortran.source.grammar.types.DeclarationTypeSpec;
import com.puresol.coding.lang.fortran.source.keywords.IntentKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ModuleKeyword;
import com.puresol.coding.lang.fortran.source.keywords.OptionalKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PointerKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ProcedureKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SaveKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1213 proc-attr-spec is access-spec
 * or proc-language-binding-spec
 * or INTENT ( intent-spec )
 * or OPTIONAL
 * or POINTER
 * or SAVE
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProcAttrSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(AccessSpec.class) != null) {
		} else if (acceptPart(ProcLanguageBindingSpec.class) != null) {
		} else if (acceptToken(IntentKeyword.class) != null) {
			expectToken(LParen.class);
			expectPart(IntentSpec.class);
			expectToken(RParen.class);
		} else if (acceptToken(OptionalKeyword.class) != null) {
		} else if (acceptToken(PointerKeyword.class) != null) {
		} else if (acceptToken(SaveKeyword.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
