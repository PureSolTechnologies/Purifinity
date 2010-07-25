package com.puresol.coding.lang.fortran.source.grammar.highlevel;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.ForallConstruct;
import com.puresol.coding.lang.fortran.source.grammar.expressions.WhereConstruct;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R213 executable-construct is action-stmt
 * or associate-construct
 * or block-construct
 * or case-construct
 * or critical-construct
 * or do-construct
 * or forall-construct
 * or if-construct
 * or select-type-construct
 * or where-construct
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ExecutableConstruct extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ActionStmt.class) != null) {
		} else if (acceptPart(AssociateConstruct.class) != null) {
		} else if (acceptPart(BlockConstruct.class) != null) {
		} else if (acceptPart(CaseConstruct.class) != null) {
		} else if (acceptPart(CriticalConstruct.class) != null) {
		} else if (acceptPart(DoConstruct.class) != null) {
		} else if (acceptPart(ForallConstruct.class) != null) {
		} else if (acceptPart(IfConstruct.class) != null) {
		} else if (acceptPart(SelectTypeConstruct.class) != null) {
		} else if (acceptPart(WhereConstruct.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
