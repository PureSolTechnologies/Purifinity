package com.puresoltechnologies.purifinity.coding.lang.java7.grammar.parts;

import com.puresoltechnologies.commons.trees.impl.TreeException;
import com.puresoltechnologies.parsers.impl.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRange;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRangeType;

public class NormalClassDeclaration {

	public static boolean is(UniversalSyntaxTree part) {
		return "NormalClassDeclaration".equals(part.getName());
	}

	private final UniversalSyntaxTree part;

	public NormalClassDeclaration(UniversalSyntaxTree part) {
		super();
		this.part = part;
	}

	public String getIdentifier() throws TreeException {
		UniversalSyntaxTree identifier = part.getChild("Identifier");
		if (identifier == null) {
			throw new TreeException("No Identifier child found!");
		}
		return identifier.getContent();
	}

	public CodeRange getCodeRange() throws TreeException {
		return new CodeRange(getIdentifier(), getIdentifier(),
				CodeRangeType.CLASS, part);
	}
}
