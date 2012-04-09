package com.puresol.coding.client.content;

import org.eclipse.jface.viewers.LabelProvider;

import com.puresol.trees.FileTree;

public class FileTreeLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
	if (element instanceof String) {
	    return (String) element;
	}
	FileTree input = (FileTree) element;
	return input.getName();
    }

}
