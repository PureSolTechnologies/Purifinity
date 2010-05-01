package com.puresol.coding.lang.java.source.parts.packages;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.parts.InterfaceDeclaration;
import com.puresol.coding.lang.java.source.parts.classes.ClassDeclaration;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class TypeDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	private static final List<Class<? extends Parser>> TYPE_DECLARATIONS = new ArrayList<Class<? extends Parser>>();
	static {
		TYPE_DECLARATIONS.add(ClassDeclaration.class);
		TYPE_DECLARATIONS.add(InterfaceDeclaration.class);
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(Semicolon.class) != null) {

		} else {
			expectOnePartOf(TYPE_DECLARATIONS);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
