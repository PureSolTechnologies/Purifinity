package com.puresol.uhura.ust.packages;

import java.util.ArrayList;
import java.util.List;

import javax.i18n4java.Translator;

import com.puresol.uhura.ust.UniversalSyntaxTree;
import com.puresol.uhura.ust.facilities.CompilerRelevantElement;

/**
 * This is a name space like found in C++ or C#. Name spaces do not have a name
 * to directory correlation like packages. For package declaration an own class
 * is provided. For name spaces an own class is provided.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class NameSpace extends CompilerRelevantElement {

	private static final long serialVersionUID = 4747868405422668380L;

	private static final Translator translator = Translator
			.getTranslator(NameSpace.class);
	private final String nameSpaceName;

	public NameSpace(String originalSymbol, String nameSpaceName) {
		super(originalSymbol);
		this.nameSpaceName = nameSpaceName;
	}

	@Override
	public final String getName() {
		return translator.i18n("Name Space");
	}

	public final String getNameSpaceName() {
		return nameSpaceName;
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
