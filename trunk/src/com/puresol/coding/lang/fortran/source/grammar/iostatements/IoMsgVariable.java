package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.ScalarDefaultCharVariable;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R907 iomsg-variable is scalar-default-char-variable
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IoMsgVariable extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(ScalarDefaultCharVariable.class);
		finish();
	}

}
