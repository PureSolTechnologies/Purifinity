package com.puresol.purifinity.uhura.ust.test;

import java.io.File;

import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.ust.CompilationUnit;
import com.puresol.purifinity.uhura.ust.USTCreator;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;

public class CompilationUnitCreator implements USTCreator {

	@Override
	public UniversalSyntaxTree createUST(ParserTree parserTree) {
		return new CompilationUnit(new File(""), "Java", "1.6");
	}

}
