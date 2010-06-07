package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * enumConstants : enumConstant (',' enumConstant )* ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EnumConstants extends AbstractJavaParser {

	private static final long serialVersionUID = -5500980743550485400L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(EnumConstant.class);
		while (acceptToken(Comma.class) != null) {
			if (acceptPart(EnumConstant.class) == null) {
				break;
			}
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
