package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.ScalarIntVariable;
import com.puresol.coding.lang.fortran.source.grammar.lexical.Label;
import com.puresol.coding.lang.fortran.source.keywords.EndKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EorKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ErrKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IdKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IoMsgKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IoStatKeyword;
import com.puresol.coding.lang.fortran.source.keywords.UnitKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R923 wait-spec is [ UNIT = ] file-unit-number
 * or END = label
 * or EOR = label
 * or ERR = label
 * or ID = scalar-int-expr
 * or IOMSG = iomsg-variable
 * or IOSTAT = scalar-int-variable
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class WaitSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(UnitKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(FileUnitNumber.class);
		} else if (acceptToken(EndKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(Label.class);
		} else if (acceptToken(EorKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(Label.class);
		} else if (acceptToken(ErrKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(Label.class);
		} else if (acceptToken(IdKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(IdVariable.class);
		} else if (acceptToken(IoMsgKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(IoMsgVariable.class);
		} else if (acceptToken(IoStatKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarIntVariable.class);
		} else {
			expectPart(FileUnitNumber.class);
		}
		finish();
	}

}
