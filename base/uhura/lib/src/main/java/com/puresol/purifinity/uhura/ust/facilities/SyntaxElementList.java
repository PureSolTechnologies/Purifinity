package com.puresol.purifinity.uhura.ust.facilities;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;

/**
 * This class represents a typed list like for parameter lists in method
 * declarations, initialized lists in Java-style for-loops or statement lists in
 * code blocks.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            is the type or interface of the elements contained in that list.
 */
public class SyntaxElementList<T extends UniversalSyntaxTree> extends
	CompilerRelevantElement {

    private static final long serialVersionUID = -1875399758682948173L;

    private final List<UniversalSyntaxTree> children = new ArrayList<UniversalSyntaxTree>();

    /**
     * This constructor is inherited from AbstractUniversalSyntaxTree.
     * 
     * @param parent
     * @param originalSymbol
     *            could here be ',', ';' or something similar regarding the
     *            source programming language.
     */
    public SyntaxElementList(String originalSymbol) {
	super(originalSymbol);
    }

    public final void addElement(T element) {
	children.add(element);
    }

    @Override
    public String getName() {
	return "Syntax Element List";
    }

    @Override
    public final boolean hasChildren() {
	return !children.isEmpty();
    }

    @Override
    public final List<UniversalSyntaxTree> getChildren() {
	return children;
    }

}