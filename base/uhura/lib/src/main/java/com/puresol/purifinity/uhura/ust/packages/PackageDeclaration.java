package com.puresol.purifinity.uhura.ust.packages;

import com.puresol.purifinity.uhura.ust.AbstractUniversalSyntaxTree;

/**
 * This is a package declaration like found in Java, Scala, D and other
 * languages. This packaged declaration also has a connection to the project's
 * directory structure. name spaces in contrast do not have such a correlation.
 * For name spaces an own class is provided.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PackageDeclaration extends AbstractUniversalSyntaxTree {

	private static final long serialVersionUID = -828922003801631227L;

	private final String packageName;

	public PackageDeclaration(String originalSymbol, String packageName) {
		super(originalSymbol);
		this.packageName = packageName;
	}

	@Override
	public final String getName() {
		return "Package Declaration: " + packageName;
	}

	public final String getPackageName() {
		return packageName;
	}
}
