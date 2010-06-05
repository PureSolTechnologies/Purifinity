package com.puresol.coding.lang.java.source.grammar.interfaces;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * interfaceBody : '{' (interfaceBodyDeclaration )* '}' ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class InterfaceBody extends AbstractJavaParser {

	private static final long serialVersionUID = -1812295859556451418L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(LCurlyBracket.class);
		acceptPart(InterfaceBodyDeclarations.class);
		expectToken(RCurlyBracket.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
