package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * enumBody : '{' (enumConstants )? ','? (enumBodyDeclarations )? '}' ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EnumBody extends AbstractJavaParser {

    private static final long serialVersionUID = -5500980743550485400L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(LCurlyBracket.class);
	acceptPart(EnumConstants.class);
	acceptToken(Comma.class);
	acceptPart(EnumBodyDeclarations.class);
	expectToken(RCurlyBracket.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }
}
