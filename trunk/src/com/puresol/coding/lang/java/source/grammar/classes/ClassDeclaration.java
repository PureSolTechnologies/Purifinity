package com.puresol.coding.lang.java.source.grammar.classes;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * classDeclaration : normalClassDeclaration | enumDeclaration ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ClassDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -1812295859556451418L;

	private static List<Class<? extends Parser>> CLASS_DECLARATIONS = new ArrayList<Class<? extends Parser>>();
	static {
		CLASS_DECLARATIONS.add(NormalClassDeclaration.class);
		CLASS_DECLARATIONS.add(EnumDeclaration.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		Parser result = expectOnePartOf(CLASS_DECLARATIONS);
		finish(result.getName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	/**
	 * Returns a list of declared fields.
	 * 
	 * @return A list of declared fields is returned.
	 */
	public List<VariableDeclarator> getFields() {
		return getChildCodeRanges(VariableDeclarator.class);
	}

}
