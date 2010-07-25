package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R742 where-construct is where-construct-stmt
 * [ where-body-construct ] ...
 * [ masked-elsewhere-stmt
 * [ where-body-construct ] ... ] ...
 * [ elsewhere-stmt
 * [ where-body-construct ] ... ]
 * end-where-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class WhereConstruct extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(WhereConstructStmt.class);
		while (true) {
			if (acceptPart(WhereBodyConstruct.class) != null) {
			} else if (acceptPart(MaskedElsewhereStmt.class) != null) {
				while (acceptPart(WhereBodyConstruct.class) != null)
					;
			} else if (acceptPart(ElsewhereStmt.class) != null) {
				while (acceptPart(WhereBodyConstruct.class) != null)
					;
			} else {
				break;
			}
		}
		expectPart(EndWhereStmt.class);
		finish();
	}
}
