package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * formalParameterDecls : ellipsisParameterDecl | normalParameterDecl (','
 * normalParameterDecl )* | (normalParameterDecl ',' )+ ellipsisParameterDecl ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FormalParameterDeclarations extends AbstractJavaParser {

	private static final long serialVersionUID = -1812295859556451418L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(EllipsisParameterDeclaration.class) != null) {
		} else if (acceptPart(NormalParameterDeclaration.class) != null) {
			while (acceptToken(Comma.class) != null) {
				if (acceptPart(NormalParameterDeclaration.class) != null) {
				} else if (acceptPart(EllipsisParameterDeclaration.class) != null) {
					break;
				} else {
					abort();
				}
			}
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
