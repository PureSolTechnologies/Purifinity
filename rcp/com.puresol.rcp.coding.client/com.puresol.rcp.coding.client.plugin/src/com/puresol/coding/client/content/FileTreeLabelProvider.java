package com.puresol.coding.client.content;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.puresol.trees.FileTree;

public class FileTreeLabelProvider extends LabelProvider {

    private final Image folderImage = PlatformUI.getWorkbench()
	    .getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);

    @Override
    public void dispose() {
	folderImage.dispose();
    }

    @Override
    public String getText(Object element) {
	if (element instanceof String) {
	    return (String) element;
	}
	FileTree input = (FileTree) element;
	return input.getName();
    }

    @Override
    public Image getImage(Object element) {
	return folderImage;

    }
}
