package com.puresol.coding.client.common.ui.components;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.puresol.trees.Tree;

public class AreaMapData implements Tree<AreaMapData> {

    private AreaMapData parent = null;
    private final List<AreaMapData> children;
    private final String name;
    private final double value;

    public AreaMapData(String name, double value, AreaMapData... children) {
	this.name = name;
	this.value = value;
	this.children = Arrays.asList(children);
	for (AreaMapData child : this.children) {
	    child.setParent(this);
	}
    }

    @Override
    public String getName() {
	return name;
    }

    public double getValue() {
	return value;
    }

    @Override
    public List<AreaMapData> getChildren() {
	return Collections.unmodifiableList(children);
    }

    private void setParent(AreaMapData parent) {
	this.parent = parent;
    }

    @Override
    public AreaMapData getParent() {
	return parent;
    }

    @Override
    public boolean hasChildren() {
	return children.size() > 0;
    }

}
