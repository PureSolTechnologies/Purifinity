package com.puresol.uhura.ust.packages;

import java.util.ArrayList;
import java.util.List;

import com.puresol.uhura.ust.UniversalSyntaxTree;
import com.puresol.uhura.ust.facilities.CompilerRelevantElement;

/**
 * This is an import package statement like found in Java, Scala, D and other
 * languages.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ImportPackage extends CompilerRelevantElement {

    private static final long serialVersionUID = -828922003801631227L;

    private final String packageName;

    public ImportPackage(String originalSymbol, String packageName) {
	super(originalSymbol);
	this.packageName = packageName;
    }

    @Override
    public final String getName() {
	return "Import Declaration for " + packageName;
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
