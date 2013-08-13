package com.puresol.purifinity.coding.lang.java.ust;

import java.util.ArrayList;
import java.util.List;

import com.puresol.commons.trees.TreeException;
import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.ust.AbstractUSTCreator;
import com.puresol.purifinity.uhura.ust.CompilationUnit;
import com.puresol.purifinity.uhura.ust.USTCreator;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;

public class CompilationUnitCreator extends AbstractUSTCreator {

	public CompilationUnitCreator(USTCreator creator) {
		super(creator);
	}

	@Override
	public UniversalSyntaxTree createUST(ParserTree parserTree)
			throws TreeException {
		UniversalSyntaxTree packageDeclaration = createPackageDeclaration(parserTree);
		List<UniversalSyntaxTree> importDeclarations = createImportDeclarations(parserTree);
		List<UniversalSyntaxTree> typeDeclarations = createTypeDeclarations(parserTree);
		return new CompilationUnit(packageDeclaration, importDeclarations,
				typeDeclarations);
	}

	private UniversalSyntaxTree createPackageDeclaration(ParserTree parserTree)
			throws TreeException {
		ParserTree packageDeclarationNode = parserTree
				.getChild("PackageDeclaration");
		UniversalSyntaxTree packageDeclaration = null;
		if (packageDeclarationNode != null) {
			packageDeclaration = getParentCreator().createUST(
					packageDeclarationNode);
		}
		return packageDeclaration;
	}

	private List<UniversalSyntaxTree> createImportDeclarations(
			ParserTree parserTree) throws TreeException {
		List<UniversalSyntaxTree> importDeclarations = new ArrayList<UniversalSyntaxTree>();
		ParserTree importDeclarationsNode = parserTree
				.getChild("ImportDeclarations");
		if (importDeclarationsNode != null) {
			importDeclarations
					.addAll(createImportDeclarations(importDeclarationsNode));
			ParserTree importDeclarationNode = importDeclarationsNode
					.getChild("ImportDeclaration");
			UniversalSyntaxTree importDeclaration = getParentCreator()
					.createUST(importDeclarationNode);
			importDeclarations.add(importDeclaration);
		}
		return importDeclarations;
	}

	private List<UniversalSyntaxTree> createTypeDeclarations(
			ParserTree parserTree) throws TreeException {
		List<UniversalSyntaxTree> typeDeclarations = new ArrayList<UniversalSyntaxTree>();
		ParserTree typeDeclarationsNode = parserTree
				.getChild("TypeDeclarations");
		if (typeDeclarationsNode != null) {
			typeDeclarations
					.addAll(createTypeDeclarations(typeDeclarationsNode));
			ParserTree typeDeclarationNode = typeDeclarationsNode
					.getChild("TypeDeclaration");
			UniversalSyntaxTree typeDeclaration = getParentCreator().createUST(
					typeDeclarationNode);
			typeDeclarations.add(typeDeclaration);
		}
		return typeDeclarations;
	}
}
