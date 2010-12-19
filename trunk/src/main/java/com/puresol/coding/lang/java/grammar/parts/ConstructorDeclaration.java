package com.puresol.coding.lang.java.grammar.parts;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.trees.TreeException;
import com.puresol.uhura.ast.ParserTree;

public class ConstructorDeclaration {

	public static boolean is(ParserTree part) {
		return "ConstructorDeclaration".equals(part.getName());
	}

	private final ParserTree part;

	public ConstructorDeclaration(ParserTree part) {
		super();
		this.part = part;
	}

	public String getName() throws TreeException {
		ParserTree name = part.getChild("ConstructorDeclarator");
		name = name.getChild("Name");
		StringBuffer nameBuffer = new StringBuffer();
		for (ParserTree namePart : name.getChildren()) {
			nameBuffer.append(namePart.getText());
		}
		return nameBuffer.toString();
	}

	public CodeRange getCodeRange() throws TreeException {
		return new CodeRange(getName(), CodeRangeType.CONSTRUCTOR, part);
	}
}
