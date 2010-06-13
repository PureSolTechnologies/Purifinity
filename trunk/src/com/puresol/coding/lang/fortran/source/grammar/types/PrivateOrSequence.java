package com.puresol.coding.lang.fortran.source.grammar.types;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.AbstractKeyword;
import com.puresol.coding.lang.fortran.source.keywords.BindKeyword;
import com.puresol.coding.lang.fortran.source.keywords.CKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ExtendsKeyword;
import com.puresol.coding.lang.fortran.source.keywords.TypeKeyword;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R428 private-or-sequence is private-components-stmt
 * or sequence-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PrivateOrSequence extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(PrivateComponentsStmt.class) != null) {
		} else if (acceptPart(SequenceStmt.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
