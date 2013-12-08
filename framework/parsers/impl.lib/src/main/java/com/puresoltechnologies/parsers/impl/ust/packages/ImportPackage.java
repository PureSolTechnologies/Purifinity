package com.puresoltechnologies.parsers.impl.ust.packages;

import com.puresoltechnologies.parsers.impl.ust.AbstractProduction;

/**
 * This is an import package statement like found in Java, Scala, D and other
 * languages.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ImportPackage extends AbstractProduction {

	private static final long serialVersionUID = -828922003801631227L;

	private final String packageName;

	public ImportPackage(String originalSymbol, String packageName) {
		super("Import Declaration", originalSymbol);
		this.packageName = packageName;
	}

	public final String getPackageName() {
		return packageName;
	}
}
