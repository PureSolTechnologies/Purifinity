package com.puresoltechnologies.purifinity.coding.lang.java7.grammar.parts;

import java.util.List;

import com.puresoltechnologies.commons.trees.api.TreeException;
import com.puresoltechnologies.parsers.impl.ust.USTUtils;
import com.puresoltechnologies.parsers.impl.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRange;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRangeType;

public class ConstructorDeclaration {

	public static boolean is(UniversalSyntaxTree part) {
		return "ConstructorDeclaration".equals(part.getName());
	}

	private final UniversalSyntaxTree part;

	public ConstructorDeclaration(UniversalSyntaxTree part) {
		super();
		this.part = part;
	}

	public String getName() throws TreeException {
		UniversalSyntaxTree constructorDeclarator = part
				.getChild("ConstructorDeclarator");
		UniversalSyntaxTree name = constructorDeclarator.getChild("Name");
		return name.getChild("Identifier").getContent();
	}

	/**
	 * This method returns a canonical identifier for method which are
	 * overloaded.
	 * 
	 * @return
	 * @throws TreeException
	 */
	public String getCanonicalName() throws TreeException {
		UniversalSyntaxTree constructorDeclarator = part
				.getChild("ConstructorDeclarator");
		UniversalSyntaxTree name = constructorDeclarator.getChild("Name")
				.getChild("Identifier");
		StringBuilder parameterTypes = new StringBuilder();
		if (constructorDeclarator.hasChild("FormalParameterList")) {
			UniversalSyntaxTree formalParameterList = constructorDeclarator
					.getChild("FormalParameterList");
			List<UniversalSyntaxTree> formalParameters = USTUtils.getSubTrees(
					formalParameterList, "FormalParameter");
			for (UniversalSyntaxTree formalParameter : formalParameters) {
				UniversalSyntaxTree type = formalParameter.getChild("Type");
				if (parameterTypes.length() > 0) {
					parameterTypes.append(",");
				}
				parameterTypes.append(type.getContent().trim());
			}
			if (formalParameterList.hasChild("LastFormalParameter")) {
				UniversalSyntaxTree lastFormalParameter = formalParameterList
						.getChild("LastFormalParameter");
				if (parameterTypes.length() > 0) {
					parameterTypes.append(",");
				}
				UniversalSyntaxTree type = lastFormalParameter.getChild("Type");
				parameterTypes.append(type.getContent().trim());
				if (lastFormalParameter.hasChild("DOT")) {
					parameterTypes.append("...");
				}
			}
		}
		return name.getContent() + "(" + parameterTypes + ")".trim();
	}

	public CodeRange getCodeRange() throws TreeException {
		return new CodeRange(getName(), getCanonicalName(),
				CodeRangeType.CONSTRUCTOR, part);
	}
}
