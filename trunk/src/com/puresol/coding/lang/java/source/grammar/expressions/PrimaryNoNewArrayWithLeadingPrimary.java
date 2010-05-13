package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * This part is a part which causes endless loops. Thias was taken outside to
 * find a better way to deal with this situation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PrimaryNoNewArrayWithLeadingPrimary extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(QualifiedClassInstanceCreationExpression.class) != null) {
		} else if (acceptPart(FieldAccess.class) != null) {
		} else if (acceptPart(MethodInvocation.class) != null) {
		} else if (acceptPart(ArrayAccess.class) != null) {
		} else {
			throw new PartDoesNotMatchException(this);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
