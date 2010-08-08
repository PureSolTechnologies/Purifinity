package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.ClassDeclaration;
import com.puresol.coding.lang.java.source.grammar.interfaces.InterfaceDeclaration;
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

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ClassDeclaration.class) != null) {
		} else if (acceptPart(InterfaceDeclaration.class) != null) {
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
