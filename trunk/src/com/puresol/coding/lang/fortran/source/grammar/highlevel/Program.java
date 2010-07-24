package com.puresol.coding.lang.fortran.source.grammar.highlevel;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.PrivateKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PublicKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R201 program is program-unit
 * [ program-unit ] ...
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Program extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.PROGRAM;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(ProgramUnit.class);
		while (acceptPart(ProgramUnit.class) != null)
			;
		finish();
	}
}
