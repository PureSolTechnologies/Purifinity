package com.puresol.coding.lang.java.source.grammar.interfaces;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.EnumDeclaration;
import com.puresol.coding.lang.java.source.grammar.classes.NormalClassDeclaration;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * 
 * NOTE: here use interfaceFieldDeclaration for field declared inside
 * annotation. they are sytactically the same.
 * 
 * nnotationTypeElementDeclaration : annotationMethodDeclaration |
 * interfaceFieldDeclaration | normalClassDeclaration |
 * normalInterfaceDeclaration | enumDeclaration | annotationTypeDeclaration |
 * ';' ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnnotationTypeElementDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -2656071830287957232L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(AnnotationMethodDeclaration.class) != null) {
		} else if (acceptPart(InterfaceFieldDeclaration.class) != null) {
		} else if (acceptPart(NormalClassDeclaration.class) != null) {
		} else if (acceptPart(NormalInterfaceDeclaration.class) != null) {
		} else if (acceptPart(EnumDeclaration.class) != null) {
		} else if (acceptPart(AnnotationTypeDeclaration.class) != null) {
		} else if (acceptToken(Semicolon.class) != null) {
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
