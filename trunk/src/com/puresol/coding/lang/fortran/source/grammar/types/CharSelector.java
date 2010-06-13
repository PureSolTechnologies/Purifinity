package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.CharacterKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ComplexKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DoublePrecisionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IntegerKeyword;
import com.puresol.coding.lang.fortran.source.keywords.KindKeyword;
import com.puresol.coding.lang.fortran.source.keywords.LenKeyword;
import com.puresol.coding.lang.fortran.source.keywords.LogicalKeyword;
import com.puresol.coding.lang.fortran.source.keywords.RealKeyword;
import com.puresol.coding.lang.fortran.source.literals.IntegerLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R420 char-selector is length-selector
 * or ( LEN = type-param-value ,
 * KIND = scalar-int-constant-expr )
 * or ( type-param-value ,
 * [ KIND = ] scalar-int-constant-expr )
 * or ( KIND = scalar-int-constant-expr
 * [ , LEN =type-param-value ] )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CharSelector extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(LengthSelector.class) != null) {
		} else {
			expectToken(LParen.class);
			if (acceptToken(LenKeyword.class) != null) {
				expectToken(Equals.class);
				expectPart(TypeParamValue.class);
				expectToken(Comma.class);
				expectToken(KindKeyword.class);
				expectToken(Equals.class);
				expectPart(ScalarIntConstantExpression.class);
			} else if (acceptToken(KindKeyword.class) != null) {
				expectToken(Equals.class);
				expectPart(ScalarIntConstantExpression.class);
				if (acceptToken(Comma.class) != null) {
					expectToken(Equals.class);
					expectPart(TypeParamValue.class);
				}
			} else {
				expectPart(TypeParamValue.class);
				expectToken(Comma.class);
				if (acceptToken(KindKeyword.class) != null) {
					expectToken(Equals.class);
				}
				expectPart(ScalarIntConstantExpression.class);
			}
			expectToken(RParen.class);
		}
		finish();
	}

}
