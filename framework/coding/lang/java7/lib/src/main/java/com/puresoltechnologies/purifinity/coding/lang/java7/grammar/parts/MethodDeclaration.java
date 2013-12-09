package com.puresoltechnologies.purifinity.coding.lang.java7.grammar.parts;

import java.util.List;

import com.puresoltechnologies.commons.trees.api.Tree;
import com.puresoltechnologies.commons.trees.api.TreeException;
import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.impl.ust.USTUtils;
import com.puresoltechnologies.purifinity.analysis.api.CodeRange;
import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;

public class MethodDeclaration {

	public static boolean is(UniversalSyntaxTree part) {
		return "MethodDeclaration".equals(part.getName());
	}

	private final UniversalSyntaxTree part;

	public MethodDeclaration(UniversalSyntaxTree part) {
		super();
		this.part = part;
	}

	/**
	 * This method simply returns the name of the method. In cases of overloaded
	 * method this is not unique!
	 * 
	 * @return A {@link String} is returned containing the name of the method.
	 * @throws TreeException
	 *             is thrown if the {@link Tree} was not in shape as expected.
	 */
	public String getIdentifier() throws TreeException {
		UniversalSyntaxTree methodHeader = part.getChild("MethodHeader");
		UniversalSyntaxTree methodDeclarator = methodHeader
				.getChild("MethodDeclarator");
		UniversalSyntaxTree identifier = methodDeclarator
				.getChild("Identifier");
		return identifier.getContent();
	}

	/**
	 * This method returns a canonical identifier for method which are
	 * overloaded.
	 * 
	 * @return
	 * @throws TreeException
	 */
	public String getCanonicalIdentifier() throws TreeException {
		UniversalSyntaxTree methodHeader = part.getChild("MethodHeader");
		UniversalSyntaxTree methodDeclarator = methodHeader
				.getChild("MethodDeclarator");
		UniversalSyntaxTree identifier = methodDeclarator
				.getChild("Identifier");
		StringBuilder parameterTypes = new StringBuilder();
		if (methodDeclarator.hasChild("FormalParameterList")) {
			UniversalSyntaxTree formalParameterList = methodDeclarator
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
		return identifier.getContent() + "(" + parameterTypes + ")";
	}

	public CodeRange getCodeRange() throws TreeException {
		return new CodeRange(getIdentifier(), getCanonicalIdentifier(),
				CodeRangeType.METHOD, part);
	}
}
