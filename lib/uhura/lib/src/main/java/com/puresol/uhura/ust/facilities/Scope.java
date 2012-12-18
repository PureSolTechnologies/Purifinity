package com.puresol.uhura.ust.facilities;

import java.util.ArrayList;
import java.util.List;

import com.puresol.uhura.ust.UniversalSyntaxTree;

/**
 * This tree element signals a scope for variable and element validity. This
 * scope is introduced everywhere where a new scope is starting. The elements
 * within the scope are children of the this class.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Scope extends CompilerRelevantElement {

    private static final long serialVersionUID = 7165390340532196126L;

    private UniversalSyntaxTree child = null;

    public Scope() {
	super("scope");
    }

    /**
     * This method sets the content of the scope. This is just one element like
     * a single loop, a code block or a single statement if needed.
     * 
     * @param child
     */
    public void setContent(UniversalSyntaxTree child) {
	this.child = child;
    }

    @Override
    public String getName() {
	return "Scope";
    }

    @Override
    public boolean hasChildren() {
	return (child != null);
    }

    @Override
    public List<UniversalSyntaxTree> getChildren() {
	List<UniversalSyntaxTree> list = new ArrayList<UniversalSyntaxTree>();
	list.add(child);
	return list;
    }

}
