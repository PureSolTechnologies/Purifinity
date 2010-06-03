package com.puresol.coding.lang.java.source.grammar.classes;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.ClassDeclaration;
import com.puresol.coding.lang.java.source.grammar.interfaces.InterfaceDeclaration;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * classOrInterfaceDeclaration : classDeclaration | interfaceDeclaration ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ClassOrInterfaceDeclaration extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    private static final List<Class<? extends Parser>> TYPE_DECLARATIONS = new ArrayList<Class<? extends Parser>>();
    static {
	TYPE_DECLARATIONS.add(ClassDeclaration.class);
	TYPE_DECLARATIONS.add(InterfaceDeclaration.class);
    }

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectOnePartOf(TYPE_DECLARATIONS);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
