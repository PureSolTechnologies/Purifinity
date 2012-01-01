package com.puresol.coding.lang.java.grammar.parts;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.trees.TreeException;
import com.puresol.uhura.parser.ParserTree;

public class EnumDeclaration {

	public static boolean is(ParserTree part) {
		return "EnumDeclaration".equals(part.getName());
	}

	private final ParserTree part;

	public EnumDeclaration(ParserTree part) {
		super();
		this.part = part;
	}

	public String getIdentifier() throws TreeException {
		ParserTree identifier = part.getChild("Identifier");
		if (identifier == null) {
			throw new TreeException("No Identifier child found!");
		}
		return identifier.getText();
	}

	public CodeRange getCodeRange() throws TreeException {
		return new CodeRange(getIdentifier(), CodeRangeType.ENUMERATION, part);
	}
}
