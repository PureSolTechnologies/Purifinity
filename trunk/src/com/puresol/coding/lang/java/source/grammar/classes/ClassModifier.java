package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.interfaces.Annotation;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * (annotation | classModifier)
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ClassModifier extends AbstractJavaParser {

	private static final long serialVersionUID = -5845018908537488666L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Annotation.class) != null) {
		} else {
			expectOneTokenOf(com.puresol.coding.lang.java.source.tokengroups.ClassModifiers.DEFINITIONS);
		}
		finish();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
