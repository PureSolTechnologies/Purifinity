package com.puresoltechnologies.purifinity.server.common.ui.navigation;

import java.io.Serializable;
import java.net.URL;
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

	private final NavigationItem parent;
	private final List<NavigationItem> children = new ArrayList<NavigationItem>();

	private final String name;
	private final URL url;

	public NavigationItem(NavigationItem parent, String name, URL url) {
		super();
		this.parent = parent;
		this.name = name;
		this.url = url;
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

	@Override
	public String getName() {
		return name;
	}

	public URL getUrl() {
		return url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

}
