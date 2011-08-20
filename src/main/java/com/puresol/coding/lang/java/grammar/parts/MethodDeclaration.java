package com.puresol.coding.lang.java.grammar.parts;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.trees.TreeException;
import com.puresol.uhura.parser.ParserTree;

public class MethodDeclaration {

	public static boolean is(ParserTree part) {
		return "MethodDeclaration".equals(part.getName());
	}

	private final ParserTree part;

	public MethodDeclaration(ParserTree part) {
		super();
		this.part = part;
	}

	public String getIdentifier() throws TreeException {
		ParserTree methodHeader = part.getChild("MethodHeader");
		ParserTree methodDeclarator = methodHeader.getChild("MethodDeclarator");
		ParserTree identifier = methodDeclarator.getChild("Identifier");
		return identifier.getText();

	}

	public CodeRange getCodeRange() throws TreeException {
		return new CodeRange(getIdentifier(), CodeRangeType.METHOD, part);
	}
}
