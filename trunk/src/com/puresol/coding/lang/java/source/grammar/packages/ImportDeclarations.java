package com.puresol.coding.lang.java.source.grammar.packages;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * importDeclaration : 'import' ('static' )? IDENTIFIER '.' '*' ';' | 'import'
 * ('static' )? IDENTIFIER ('.' IDENTIFIER )+ ('.' '*' )? ';' ;
 * 
 * @author Rick-Rainer Ludwig
 */
public class ImportDeclarations extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectPart(ImportDeclaration.class);
	while (acceptPart(ImportDeclaration.class) != null)
	    ;
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
