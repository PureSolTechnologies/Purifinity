package com.puresol.coding.lang.fortran.source.grammar.highlevel;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.procedures.ImportStmt;
import com.puresol.coding.lang.fortran.source.grammar.programunits.UseStmt;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R204 specification-part is [ use-stmt ] ...
 * [ import-stmt ] ...
 * [ implicit-part ]
 * [ declaration-construct ] ...
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SpecificationPart extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		while (true) {
			if (acceptPart(UseStmt.class) != null) {
			} else if (acceptPart(ImportStmt.class) != null) {
			} else if (acceptPart(ImplicitPart.class) != null) {
			} else if (acceptPart(DeclarationConstruct.class) != null) {
			} else {
				break;
			}
		}
		finish();
	}

}
