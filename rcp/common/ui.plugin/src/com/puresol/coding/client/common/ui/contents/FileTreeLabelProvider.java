package com.puresol.coding.client.common.ui.contents;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.puresol.coding.client.common.ui.Activator;
import com.puresol.coding.client.common.ui.ClientImages;
import com.puresol.trees.FileTree;

public class FileTreeLabelProvider extends LabelProvider {

	private final Image databaseFolderImage = Activator.getDefault()
			.getImageRegistry().get(ClientImages.FOLDER_16x16);

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
		return databaseFolderImage;

	}
}
