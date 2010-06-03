package com.puresol.coding.lang.java.source.grammar.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * interfaceDeclaration : normalInterfaceDeclaration | annotationTypeDeclaration
 * ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class InterfaceDeclaration extends AbstractJavaParser {

    private static final long serialVersionUID = -1812295859556451418L;

    private static List<Class<? extends Parser>> INTERFACE_DECLARATIONS = new ArrayList<Class<? extends Parser>>();
    static {
	INTERFACE_DECLARATIONS.add(NormalInterfaceDeclaration.class);
	INTERFACE_DECLARATIONS.add(AnnotationTypeDeclaration.class);
    }

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	Parser result = expectOnePartOf(INTERFACE_DECLARATIONS);
	finish(result.getName());
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }
}
