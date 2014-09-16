package com.puresoltechnologies.purifinity.server.common.ui.navigation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.commons.trees.Tree;

/**
 * This class represents a single navigation item which contains of a name and a
 * URL.
 * 
 * @author Rick-Rainer Ludwig
 */
public class NavigationItem implements Tree<NavigationItem>, Serializable {

    private static final long serialVersionUID = -7143447105599029788L;

    private final List<NavigationItem> children = new ArrayList<NavigationItem>();

    private NavigationItem parent = null;

    private final String id;
    private final String name;
    private final String navigationTarget;
    private final boolean internal;

    public NavigationItem(String id, String name, String navigationTarget) {
	this(id, name, navigationTarget, true);
    }

    public NavigationItem(String id, String name, String navigationTarget,
	    boolean internal) {
	super();
	this.id = id;
	this.name = name;
	this.navigationTarget = navigationTarget;
	this.internal = internal;
    }

    public void setParent(NavigationItem parent) {
	this.parent = parent;
    }

    @Override
    public NavigationItem getParent() {
	return parent;
    }

    @Override
    public boolean hasChildren() {
	return !children.isEmpty();
    }

    @Override
    public List<NavigationItem> getChildren() {
	return children;
    }

    public String getId() {
	return id;
    }

    @Override
    public String getName() {
	return name;
    }

    public String getNavigationTarget() {
	return navigationTarget;
    }

    public boolean isInternal() {
	return internal;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	NavigationItem other = (NavigationItem) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
