package com.puresol.uhura.ust;

import javax.i18n4java.Translator;

public class PackageDeclaration extends AbstractUniversalSyntaxTree {

	private static final Translator translator = Translator
			.getTranslator(PackageDeclaration.class);
	private final String packageName;

	public PackageDeclaration(AbstractUniversalSyntaxTree parent,
			String originalSymbol, String packageName) {
		super(parent, originalSymbol);
		this.packageName = packageName;
	}

	@Override
	public String getName() {
		return translator.i18n("Package Declaration");
	}

	public String getPackageName() {
		return packageName;
	}

}
