package com.puresol.purifinity.uhura.ust;

import java.util.List;

public class CompilationUnit extends AbstractUniversalSyntaxTree {

	private static final long serialVersionUID = -5790049234290910253L;

	public CompilationUnit(UniversalSyntaxTree packageDeclaration,
			List<UniversalSyntaxTree> importDeclarations,
			List<UniversalSyntaxTree> typeDeclarations) {
		super("");
		addChild(packageDeclaration);
		addChildren(importDeclarations);
		addChildren(typeDeclarations);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return "Compilation Unit";
	}

}
