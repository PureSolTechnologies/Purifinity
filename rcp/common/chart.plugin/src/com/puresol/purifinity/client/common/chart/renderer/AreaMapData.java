package com.puresol.purifinity.client.common.chart.renderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.puresol.commons.trees.Tree;

public class AreaMapData implements Tree<AreaMapData>, Comparable<AreaMapData> {

	private AreaMapData parent = null;
	private final List<AreaMapData> children;
	private final String name;
	private final double value;
	private final Object secondaryValue;

	public AreaMapData(String name, AreaMapData... children) {
		this(name, null, children);
	}

	public AreaMapData(String name, double value, AreaMapData... children) {
		this(name, value, null, children);
	}

	public AreaMapData(String name, double value) {
		this(name, value, (Object) null);
	}

	public AreaMapData(String name, Object secondaryValue,
			AreaMapData... children) {
		this.name = name;
		this.children = Arrays.asList(children);
		double value = 0.0;
		for (AreaMapData child : this.children) {
			child.setParent(this);
			value += child.getValue();
		}
		this.value = value;
		this.secondaryValue = secondaryValue;
	}

	public AreaMapData(String name, double value, Object secondaryValue,
			AreaMapData... children) {
		this.name = name;
		this.value = value;
		this.children = Arrays.asList(children);
		this.secondaryValue = secondaryValue;
	}

	public AreaMapData(String name, double value, Object secondaryValue) {
		this.name = name;
		this.children = new ArrayList<AreaMapData>();
		this.value = value;
		this.secondaryValue = secondaryValue;
	}

	@Override
	public String getName() {
		return name;
	}

	public double getValue() {
		return value;
	}

	public Object getSecondaryValue() {
		return secondaryValue;
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

	@Override
	public int compareTo(AreaMapData o) {
		if (value < o.value) {
			return -1;
		} else if (value > o.value) {
			return 1;
		}
		return 0;
	}

}
