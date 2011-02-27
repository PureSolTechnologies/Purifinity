package com.puresol.gui.osgi;

import java.util.ArrayList;
import java.util.List;

import com.puresol.osgi.BundleConfigurator;
import com.puresol.osgi.BundleConfiguratorManager;
import com.puresol.trees.Tree;

public class BundleConfiguratorTree implements Tree<BundleConfiguratorTree> {

	public static BundleConfiguratorTree create(String frameworkName) {
		BundleConfiguratorTree root = new BundleConfiguratorTree(null,
				"Configurators", null);
		for (BundleConfigurator configurator : BundleConfiguratorManager
				.getAll()) {
			String path[] = configurator.getPathName().split("/");
			BundleConfiguratorTree node = root;
			for (int id = 0; id < path.length; id++) {
				final String name = path[id];
				final boolean isLast = (id == path.length - 1);
				BundleConfiguratorTree child = node.findChild(name);
				if (child == null) {
					if (isLast) {
						BundleConfiguratorTree newNode = new BundleConfiguratorTree(
								node, name, configurator);
						node.addChild(newNode);
						node = newNode;
					} else {
						BundleConfiguratorTree newNode = new BundleConfiguratorTree(
								node, name, null);
						node.addChild(newNode);
						node = newNode;
					}
				} else {
					if (isLast) {
						BundleConfiguratorTree newNode = new BundleConfiguratorTree(
								node, name, configurator);
						if (node.getChildren().contains(newNode)) {
							throw new RuntimeException(
									"Two configurators are registered to a node!");
						}
						node.addChild(newNode);
					} else {
						node = child;
					}
				}
			}
		}
		return root;
	}

	private BundleConfigurator configurator;
	private final String name;
	private final int hashcode;
	private final BundleConfiguratorTree parent;
	private final List<BundleConfiguratorTree> children = new ArrayList<BundleConfiguratorTree>();

	private BundleConfiguratorTree(BundleConfiguratorTree parent, String name,
			BundleConfigurator configurator) {
		this.parent = parent;
		this.name = name;
		this.configurator = configurator;
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((configurator == null) ? 0 : configurator.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		hashcode = result;
	}

	@Override
	public String getName() {
		return name;
	}

	public BundleConfigurator getConfigurator() {
		return configurator;
	}

	public void setConfigurator(BundleConfigurator configurator) {
		this.configurator = configurator;
	}

	@Override
	public BundleConfiguratorTree getParent() {
		return parent;
	}

	@Override
	public boolean hasChildren() {
		return children.size() != 0;
	}

	@Override
	public List<BundleConfiguratorTree> getChildren() {
		return children;
	}

	private void addChild(BundleConfiguratorTree child) {
		children.add(child);
	}

	private BundleConfiguratorTree findChild(String name) {
		for (BundleConfiguratorTree child : getChildren()) {
			if (child.getName().equals(name)) {
				return child;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		return hashcode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BundleConfiguratorTree other = (BundleConfiguratorTree) obj;
		if (this.hashcode != other.hashcode) {
			return false;
		}
		if (configurator == null) {
			if (other.configurator != null)
				return false;
		} else if (!configurator.equals(other.configurator))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
