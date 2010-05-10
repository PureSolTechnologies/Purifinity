package com.puresol.coding.lang.java.source.parts.classes;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ClassDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -1812295859556451418L;

	private static List<Class<? extends Parser>> CLASS_DECLARATIONS = new ArrayList<Class<? extends Parser>>();
	static {
		CLASS_DECLARATIONS.add(NormalClassDeclaration.class);
		CLASS_DECLARATIONS.add(EnumDeclaration.class);
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		Parser result = expectOnePartOf(CLASS_DECLARATIONS);
		finish(result.getName());
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.CLASS;
	}

	public List<VariableDeclarator> getFields() {
		return getChildCodeRanges(ClassBody.class).get(0).getFields();
	}
}
