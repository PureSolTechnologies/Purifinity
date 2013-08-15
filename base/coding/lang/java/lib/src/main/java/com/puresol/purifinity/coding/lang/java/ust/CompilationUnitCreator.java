package com.puresol.purifinity.coding.lang.java.ust;

import java.util.ArrayList;
import java.util.List;

import com.puresol.commons.trees.TreeException;
import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.ust.AbstractUSTCreator;
import com.puresol.purifinity.uhura.ust.AbstractUSTNode;
import com.puresol.purifinity.uhura.ust.CompilationUnit;
import com.puresol.purifinity.uhura.ust.USTCreator;
import com.puresol.purifinity.uhura.ust.USTNode;

public class CompilationUnitCreator extends AbstractUSTCreator {

	public CompilationUnitCreator(USTCreator creator) {
		super(creator);
	}

	@Override
	public AbstractUSTNode createUST(ParserTree parserTree)
			throws TreeException {
		USTNode packageDeclaration = createPackageDeclaration(parserTree);
		List<USTNode> importDeclarations = createImportDeclarations(parserTree);
		List<USTNode> typeDeclarations = createTypeDeclarations(parserTree);
		return new CompilationUnit();
	}

	private USTNode createPackageDeclaration(ParserTree parserTree)
			throws TreeException {
		ParserTree packageDeclarationNode = parserTree
				.getChild("PackageDeclaration");
		USTNode packageDeclaration = null;
		if (packageDeclarationNode != null) {
			packageDeclaration = getParentCreator().createUST(
					packageDeclarationNode);
		}
		return packageDeclaration;
	}

	private List<USTNode> createImportDeclarations(ParserTree parserTree)
			throws TreeException {
		List<USTNode> importDeclarations = new ArrayList<>();
		ParserTree importDeclarationsNode = parserTree
				.getChild("ImportDeclarations");
		if (importDeclarationsNode != null) {
			importDeclarations
					.addAll(createImportDeclarations(importDeclarationsNode));
			ParserTree importDeclarationNode = importDeclarationsNode
					.getChild("ImportDeclaration");
			USTNode importDeclaration = getParentCreator().createUST(
					importDeclarationNode);
			importDeclarations.add(importDeclaration);
		}
		return importDeclarations;
	}

	private List<USTNode> createTypeDeclarations(ParserTree parserTree)
			throws TreeException {
		List<USTNode> typeDeclarations = new ArrayList<>();
		ParserTree typeDeclarationsNode = parserTree
				.getChild("TypeDeclarations");
		if (typeDeclarationsNode != null) {
			typeDeclarations
					.addAll(createTypeDeclarations(typeDeclarationsNode));
			ParserTree typeDeclarationNode = typeDeclarationsNode
					.getChild("TypeDeclaration");
			USTNode typeDeclaration = getParentCreator().createUST(
					typeDeclarationNode);
			typeDeclarations.add(typeDeclaration);
		}
		return typeDeclarations;
	}
}
