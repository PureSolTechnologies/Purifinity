package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * classBody : '{' (classBodyDeclaration )* '}' ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ClassBody extends AbstractJavaParser {

    private static final long serialVersionUID = -2656071830287957232L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(LCurlyBracket.class);
	acceptPart(ClassBodyDeclarations.class);
	expectToken(RCurlyBracket.class);
	finish();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }
}
