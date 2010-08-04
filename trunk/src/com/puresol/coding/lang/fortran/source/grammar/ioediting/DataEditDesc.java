package com.puresol.coding.lang.fortran.source.grammar.ioediting;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.AKeyword;
import com.puresol.coding.lang.fortran.source.keywords.BKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DTKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ENKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ESKeyword;
import com.puresol.coding.lang.fortran.source.keywords.FKeyword;
import com.puresol.coding.lang.fortran.source.keywords.GKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IKeyword;
import com.puresol.coding.lang.fortran.source.keywords.LKeyword;
import com.puresol.coding.lang.fortran.source.keywords.OKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ZKeyword;
import com.puresol.coding.lang.fortran.source.literals.CharLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1007 data-edit-desc is I w [ . m ]
 * or B w [ . m ]
 * or O w [ . m ]
 * or Z w [ . m ]
 * or F w . d
 * or E w . d [ E e ]
 * or EN w . d [ E e ]
 * or ES w . d [ E e ]
 * or G w [ . d [ E e ] ]
 * or L w
 * or A [ w ]
 * or D w . d
 * or DT [ char-literal-constant ] [ ( v-list ) ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DataEditDesc extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(IKeyword.class) != null) {
			expectPart(W.class);
			if (acceptToken(Comma.class) != null) {
				expectPart(M.class);
			}
		} else if (acceptToken(BKeyword.class) != null) {
			expectPart(W.class);
			if (acceptToken(Comma.class) != null) {
				expectPart(M.class);
			}
		} else if (acceptToken(OKeyword.class) != null) {
			expectPart(W.class);
			if (acceptToken(Comma.class) != null) {
				expectPart(M.class);
			}
		} else if (acceptToken(ZKeyword.class) != null) {
			expectPart(W.class);
			if (acceptToken(Comma.class) != null) {
				expectPart(M.class);
			}
		} else if (acceptToken(FKeyword.class) != null) {
			expectPart(W.class);
			expectToken(Comma.class);
			expectPart(D.class);
		} else if (acceptToken(EKeyword.class) != null) {
			expectPart(W.class);
			expectToken(Comma.class);
			expectPart(M.class);
			if (acceptToken(EKeyword.class) != null) {
				expectPart(E.class);
			}
		} else if (acceptToken(ENKeyword.class) != null) {
			expectPart(W.class);
			expectToken(Comma.class);
			expectPart(M.class);
			if (acceptToken(EKeyword.class) != null) {
				expectPart(D.class);
			}
		} else if (acceptToken(ESKeyword.class) != null) {
			expectPart(W.class);
			expectToken(Comma.class);
			expectPart(D.class);
			if (acceptToken(EKeyword.class) != null) {
				expectPart(E.class);
			}
		} else if (acceptToken(GKeyword.class) != null) {
			expectPart(W.class);
			if (acceptToken(Comma.class) != null) {
				expectPart(D.class);
				if (acceptToken(EKeyword.class) != null) {
					expectPart(E.class);
				}
			}
		} else if (acceptToken(LKeyword.class) != null) {
			expectPart(W.class);
		} else if (acceptToken(AKeyword.class) != null) {
			acceptPart(W.class);
		} else if (acceptToken(DKeyword.class) != null) {
			expectPart(W.class);
			expectToken(Comma.class);
			expectPart(D.class);
		} else if (acceptToken(DTKeyword.class) != null) {
			acceptToken(CharLiteral.class);
			if (acceptToken(LParen.class) != null) {
				expectPart(VList.class);
				expectToken(RParen.class);
			}
		} else {
			abort();
		}
		finish();
	}

}
