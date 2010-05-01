package com.puresol.coding.lang.java.source.parts.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Assign;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ElementValue_not_finished extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	private static final List<Class<? extends Parser>> ELEMENT_VALUES = new ArrayList<Class<? extends Parser>>();
	static {
		// TODO: ELEMENT_VALUES.add(ConditionalExpression.class);
		ELEMENT_VALUES.add(Annotation.class);
		ELEMENT_VALUES.add(ElementValueArrayInitializer.class);
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(Identifier.class);
		expectToken(Assign.class);
		expectPart(ElementValue_not_finished.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
