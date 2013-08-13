package com.puresol.purifinity.coding.lang.java.ust;

import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.ust.AbstractUSTCreator;
import com.puresol.purifinity.uhura.ust.USTCreator;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;

public class CompilationUnitCreator extends AbstractUSTCreator {

	public CompilationUnitCreator(USTCreator creator) {
		super(creator);
	}

	@Override
	public UniversalSyntaxTree createUST(ParserTree parserTree) {
		return null;
	}

}
