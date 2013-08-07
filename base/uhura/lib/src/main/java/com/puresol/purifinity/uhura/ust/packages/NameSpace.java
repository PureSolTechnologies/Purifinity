package com.puresol.purifinity.uhura.ust.packages;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;
import com.puresol.purifinity.uhura.ust.facilities.AbstractProduction;

/**
 * This is a name space like found in C++ or C#. Name spaces do not have a name
 * to directory correlation like packages. For package declaration an own class
 * is provided. For name spaces an own class is provided.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class NameSpace extends AbstractProduction {

    private static final long serialVersionUID = 4747868405422668380L;

    private final String nameSpaceName;

    public NameSpace(String originalSymbol, String nameSpaceName) {
	super(originalSymbol);
	this.nameSpaceName = nameSpaceName;
    }

    @Override
    public final String getName() {
	return "Name Space";
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
