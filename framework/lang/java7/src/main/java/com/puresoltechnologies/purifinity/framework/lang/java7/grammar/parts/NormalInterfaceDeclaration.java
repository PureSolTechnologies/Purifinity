package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts;

import com.puresoltechnologies.commons.trees.api.TreeException;
import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;

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