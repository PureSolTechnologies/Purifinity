package com.puresoltechnologies.purifinity.client.common.ui.contents;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.puresoltechnologies.purifinity.client.common.branding.ClientImages;
import com.puresoltechnologies.purifinity.framework.commons.utils.FileTree;

public class FileTreeLabelProvider extends LabelProvider {

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
		return ClientImages.getImage(ClientImages.FOLDER_16x16);
	}
}
