package com.puresol.purifinity.client.common.evaluation.metrics.halstead;

import java.util.Map.Entry;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

public class MyViewerComparator extends ViewerComparator {

	private int column;
	private int direction = SWT.DOWN;

	public MyViewerComparator() {
		this.column = 0;
	}

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

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		@SuppressWarnings("unchecked")
		Entry<String, Integer> p1 = (Entry<String, Integer>) e1;
		@SuppressWarnings("unchecked")
		Entry<String, Integer> p2 = (Entry<String, Integer>) e2;
		int rc = 0;
		switch (column) {
		case 0:
			rc = p1.getKey().toLowerCase().compareTo(p2.getKey().toLowerCase());
			break;
		case 1:
			rc = p1.getValue().compareTo(p2.getValue());
			break;
		default:
			rc = 0;
		}
		// If descending order, flip the direction
		if (direction == SWT.DOWN) {
			rc = -rc;
		}
		return rc;
	}

}