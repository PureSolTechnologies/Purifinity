package com.puresol.purifinity.uhura.ust;

import java.util.List;

public class CompilationUnit extends USTNode {

	private static final long serialVersionUID = -5790049234290910253L;

	public CompilationUnit(String originalName,
			List<UniversalSyntaxTree> ustChildren) {
		super("Compilation Unit", originalName, ustChildren);
	}

}
