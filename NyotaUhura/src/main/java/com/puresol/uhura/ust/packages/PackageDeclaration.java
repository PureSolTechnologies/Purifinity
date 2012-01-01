package com.puresol.uhura.ust.packages;

import java.util.ArrayList;
import java.util.List;

import javax.i18n4java.Translator;

import com.puresol.uhura.ust.UniversalSyntaxTree;
import com.puresol.uhura.ust.facilities.CompilerRelevantElement;

/**
 * This is a package declaration like found in Java, Scala, D and other
 * languages. This packaged declaration also has a connection to the project's
 * directory structure. name spaces in contrast do not have such a correlation.
 * For name spaces an own class is provided.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PackageDeclaration extends CompilerRelevantElement {

	private static final long serialVersionUID = -828922003801631227L;

	private static final Translator translator = Translator
			.getTranslator(PackageDeclaration.class);
	private final String packageName;

	public PackageDeclaration(String originalSymbol, String packageName) {
		super(originalSymbol);
		this.packageName = packageName;
	}

	@Override
	public final String getName() {
		return translator.i18n("Package Declaration: {0}", packageName);
	}

	public final String getPackageName() {
		return packageName;
	}

	@Override
	public final boolean hasChildren() {
		return false;
	}

	@Override
	public final List<UniversalSyntaxTree> getChildren() {
		return new ArrayList<UniversalSyntaxTree>();
	}

}
