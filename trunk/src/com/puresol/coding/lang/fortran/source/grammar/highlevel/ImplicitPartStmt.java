package com.puresol.coding.lang.fortran.source.grammar.highlevel;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.attributes.ImplicitStmt;
import com.puresol.coding.lang.fortran.source.grammar.attributes.ParameterStmt;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R206 implicit-part-stmt is implicit-stmt
 * or parameter-stmt
 * or format-stmt
 * or entry-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ImplicitPartStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ImplicitStmt.class) != null) {
		} else if (acceptPart(ParameterStmt.class) != null) {
		} else if (acceptPart(FormatStmt.class) != null) {
		} else if (acceptPart(EntryStmt.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
