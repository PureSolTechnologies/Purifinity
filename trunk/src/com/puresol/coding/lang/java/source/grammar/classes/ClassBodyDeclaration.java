package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * classBodyDeclaration : ';' | ('static' )? block | memberDecl ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ClassBodyDeclaration extends AbstractJavaParser {

    private static final long serialVersionUID = -2656071830287957232L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptToken(Semicolon.class) != null) {
	} else if (acceptPart(StaticInitializer.class) != null) {
	} else if (acceptPart(InstanceInitializer.class) != null) {
	} else if (acceptPart(MemberDeclaration.class) != null) {
	} else {
	    abort();
	}
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
