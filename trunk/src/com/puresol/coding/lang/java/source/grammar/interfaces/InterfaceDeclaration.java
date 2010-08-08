package com.puresol.coding.lang.java.source.grammar.interfaces;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
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

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(NormalInterfaceDeclaration.class) != null) {
		} else if (acceptPart(AnnotationTypeDeclaration.class) != null) {
		} else {
			abort();
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
