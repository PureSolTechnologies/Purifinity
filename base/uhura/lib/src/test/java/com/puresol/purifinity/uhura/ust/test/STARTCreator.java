package com.puresol.purifinity.uhura.ust.test;

import java.io.File;

import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.ust.AbstractUSTCreator;
import com.puresol.purifinity.uhura.ust.AbstractUniversalSyntaxTree;
import com.puresol.purifinity.uhura.ust.CompilationUnit;
import com.puresol.purifinity.uhura.ust.USTCreator;

public class STARTCreator extends AbstractUSTCreator {

	public STARTCreator(USTCreator creator) {
		super(creator);
	}

	@Override
	public AbstractUniversalSyntaxTree createUST(ParserTree parserTree) {
		return new CompilationUnit(new File(""), "Java", "1.6");
	}

}
