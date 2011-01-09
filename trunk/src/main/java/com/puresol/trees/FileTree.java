package com.puresol.trees;

import java.io.File;

/**
 * This
 * 
 * @author ludwig
 * 
 */
public class FileTree extends AbstractTreeImpl<FileTree> {

	public FileTree(FileTree parent, String name) {
		super(parent, name);
	}

	public FileTree getChild(String name) {
		for (FileTree child : getChildren()) {
			if (child.getName().equals(name)) {
				return child;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return getName();
	}

	public String getPath() {
		StringBuffer path = new StringBuffer();
		FileTree current = this;
		while (current.getParent() != null) {
			if (current != this) {
				path.insert(0, File.separator);
			}
			path.insert(0, current.getName());
			current = current.getParent();
		}
		return path.toString();
	}

	public File getPathFile() {
		return new File(getPath());
	}
}
