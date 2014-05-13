package com.puresoltechnologies.purifinity.server.plugin.java7.grammar.parts;

import com.puresoltechnologies.commons.trees.TreeException;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;

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
