package com.puresol.coding.lang.fortran.source.grammar.highlevel;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1104 module is module-stmt
 * [ specification-part ]
 * [ module-subprogram-part ]
 * end-module-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Module extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(ModuleStmt.class);
		acceptPart(SpecificationPart.class);
		acceptPart(ModuleSubprogramPart.class);
		expectPart(EndModuleStmt.class);
		finish();
	}

}
