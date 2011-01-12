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
		return getPathFile().getPath();
	}

	public File getPathFile() {
		FileTree current = this;
		File file = new File(current.getName());
		current = current.getParent();
		while (current != null) {
			file = new File(current.getName(), file.getPath());
			current = current.getParent();
		}
		return file;
	}
}
