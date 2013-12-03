package com.puresoltechnologies.purifinity.client.common.ui.contents;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;

public abstract class AbstractViewerSorter extends ViewerSorter {

	private int column = 0;
	private int direction = SWT.UP;

	public int getDirection() {
		return direction;
	}

	public void setColumn(int column) {
		if (this.column == column) {
			// Same column as last sort; toggle the direction
			direction = direction == SWT.UP ? SWT.DOWN : SWT.UP;
		} else {
			// New column; do an ascending sort
			this.column = column;
			direction = SWT.DOWN;
		}
	}

	public int getColumn() {
		return column;
	}

	@Override
	public abstract int compare(Viewer viewer, Object e1, Object e2);
}
