package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.procedures.ProcInterface;
import com.puresol.coding.lang.fortran.source.keywords.ProcedureKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R440 proc-component-def-stmt is PROCEDURE ( [ proc-interface ] ) ,
 * proc-component-attr-spec-list :: proc-decl -list
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProcedureComponentDefStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(ProcedureKeyword.class);
		expectToken(LParen.class);
		acceptPart(ProcInterface.class);
		expectToken(RParen.class);
		expectToken(Comma.class);
		expectPart(ProcComponentAttrSpecList.class);
		expectToken(Colon.class);
		expectToken(Colon.class);
		expectPart(ProcDeclList.class);
		finish();
	}
}
