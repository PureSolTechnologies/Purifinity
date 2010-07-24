package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.AllocatableKeyword;
import com.puresol.coding.lang.fortran.source.keywords.AsynchronousKeyword;
import com.puresol.coding.lang.fortran.source.keywords.CodimensionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ContiguousKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DimensionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ExternalKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IntentKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IntrinsicKeyword;
import com.puresol.coding.lang.fortran.source.keywords.OptionalKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ParameterKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PointerKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ProtectedKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SaveKeyword;
import com.puresol.coding.lang.fortran.source.keywords.TargetKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ValueKeyword;
import com.puresol.coding.lang.fortran.source.keywords.VolatileKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.LBracket;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.symbols.RBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R502 attr-spec is access-spec
 * or ALLOCATABLE
 * or ASYNCHRONOUS
 * or CODIMENSION lbracket coarray-spec rbracket
 * or CONTIGUOUS
 * or DIMENSION ( array-spec )
 * or EXTERNAL
 * or INTENT ( intent-spec )
 * or INTRINSIC
 * or language-binding-spec
 * or OPTIONAL
 * or PARAMETER
 * or POINTER
 * or PROTECTED
 * or SAVE
 * or TARGET
 * or VALUE
 * or VOLATILE
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AttrSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(AccessSpec.class) != null) {
		} else if (acceptToken(AllocatableKeyword.class) != null) {
		} else if (acceptToken(AsynchronousKeyword.class) != null) {
		} else if (acceptToken(CodimensionKeyword.class) != null) {
			expectToken(LBracket.class);
			expectPart(CoarraySpec.class);
			expectToken(RBracket.class);
		} else if (acceptToken(ContiguousKeyword.class) != null) {
		} else if (acceptToken(DimensionKeyword.class) != null) {
			expectToken(LParen.class);
			expectPart(ArraySpec.class);
			expectToken(RParen.class);
		} else if (acceptToken(ExternalKeyword.class) != null) {
		} else if (acceptToken(IntentKeyword.class) != null) {
			expectToken(LParen.class);
			expectPart(IntentSpec.class);
			expectToken(RParen.class);
		} else if (acceptToken(IntrinsicKeyword.class) != null) {
		} else if (acceptPart(LanguageBindingSpec.class) != null) {
		} else if (acceptToken(OptionalKeyword.class) != null) {
		} else if (acceptToken(ParameterKeyword.class) != null) {
		} else if (acceptToken(PointerKeyword.class) != null) {
		} else if (acceptToken(ProtectedKeyword.class) != null) {
		} else if (acceptToken(SaveKeyword.class) != null) {
		} else if (acceptToken(TargetKeyword.class) != null) {
		} else if (acceptToken(ValueKeyword.class) != null) {
		} else if (acceptToken(VolatileKeyword.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
