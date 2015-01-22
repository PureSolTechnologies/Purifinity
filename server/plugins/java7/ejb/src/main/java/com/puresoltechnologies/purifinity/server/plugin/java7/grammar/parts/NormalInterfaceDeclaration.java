package com.puresoltechnologies.purifinity.server.plugin.java7.grammar.parts;

import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.trees.TreeException;

public class NormalInterfaceDeclaration {

	public static boolean is(UniversalSyntaxTree part) {
		return "NormalInterfaceDeclaration".equals(part.getName());
	}

	private final UniversalSyntaxTree part;

	public NormalInterfaceDeclaration(UniversalSyntaxTree part) {
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
				CodeRangeType.INTERFACE, part);
	}
}
