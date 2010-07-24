package com.puresol.coding.lang.fortran.source.grammar.highlevel;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.attributes.ImplicitStmt;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R205 implicit-part is [ implicit-part-stmt ] ...
 * implicit-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ImplicitPart extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		while (acceptPart(ImplicitPartStmt.class) != null) {
		}
		expectPart(ImplicitStmt.class);
		finish();
	}

}
