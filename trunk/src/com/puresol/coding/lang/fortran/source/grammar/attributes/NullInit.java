package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.types.CharLength;
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
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.GreaterThan;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.LSquareBracket;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.symbols.RSquareBracket;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R506 null-init is function-reference
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class NullInit extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(FunctionReference.class);
		finish();
	}
}
