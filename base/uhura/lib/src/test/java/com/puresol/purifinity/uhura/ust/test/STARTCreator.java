package com.puresol.purifinity.uhura.ust.test;

import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.ust.AbstractUSTCreator;
import com.puresol.purifinity.uhura.ust.CompilationUnit;
import com.puresol.purifinity.uhura.ust.USTCreator;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;

public class STARTCreator extends AbstractUSTCreator {

	public STARTCreator(USTCreator creator) {
		super(creator);
	}

	@Override
	public UniversalSyntaxTree createUST(ParserTree parserTree) {
		return new CompilationUnit();
	}

}
