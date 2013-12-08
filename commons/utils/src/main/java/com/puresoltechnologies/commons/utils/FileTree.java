package com.puresoltechnologies.commons.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.commons.trees.impl.AbstractTreeImpl;

/**
 * This is a special implementation of a tree for files. This tree can be used
 * to represent file system parts. This tree is designed to be immutable!
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileTree extends AbstractTreeImpl<FileTree> implements
		Comparable<FileTree> {

	public static List<File> getFileListFromFileTree(File baseDirectory,
			FileTree fileTree) {
		List<File> files = new ArrayList<File>();
		for (FileTree fileTreeNode : fileTree) {
			File file = fileTreeNode.getPathFile(false);
			if (new File(baseDirectory, file.getPath()).isFile()) {
				files.add(file);
			}
		}
		return files;
	}

	/**
	 * This is the initial value constructor.
	 * 
	 * @param parent
	 *            is the parent element where this new node is to be added to.
	 * @param name
	 *            is the name of the node eg. the identifier.
	 */
	public FileTree(FileTree parent, String name) {
		super(parent, name);
	}

	/**
	 * This method returns the node with the specified name.
	 * 
	 * @param name
	 * @return
	 */
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

	/**
	 * This method returns the Path of the node as string.
	 * 
	 * @param includeRootElement
	 *            specifies whether the root element of the tree needs to be
	 *            added to the returned path, too, or not. This would make the
	 *            return value an absolute path. True adds the root element.
	 *            False, does not.
	 * @return
	 */
	public String getPath(boolean includeRootElement) {
		return getPathFile(includeRootElement).getPath();
	}

	/**
	 * This method returns the Path of the node as File.
	 * 
	 * @param includeRootElement
	 *            specifies whether the root element of the tree needs to be
	 *            added to the returned path, too, or not. This would make the
	 *            return value an absolute path. True adds the root element.
	 *            False, does not.
	 * @return
	 */
	public File getPathFile(boolean includeRootElement) {
		if (getParent() == null) {
			return new File("");
		}
		FileTree current = this;
		File file;
		file = new File(current.getName());
		current = current.getParent();
		while (current != null) {
			FileTree parent = current.getParent();
			if ((includeRootElement) || (parent != null)) {
				if (!current.getName().isEmpty()) {
					file = new File(current.getName(), file.getPath());
				}
			}
			current = parent;
		}
		return file;
	}

	@Override
	public int compareTo(FileTree o) {
		return getName().compareTo(o.getName());
	}

}
