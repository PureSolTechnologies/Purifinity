package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.halstead;

import java.util.Map.Entry;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;

import com.puresoltechnologies.purifinity.client.common.ui.contents.AbstractViewerSorter;

public class OperatorsAndOperandsViewerSorter extends AbstractViewerSorter {

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		@SuppressWarnings("unchecked")
		Entry<String, Integer> p1 = (Entry<String, Integer>) e1;
		@SuppressWarnings("unchecked")
		Entry<String, Integer> p2 = (Entry<String, Integer>) e2;
		int rc = 0;
		switch (getColumn()) {
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
		if (getDirection() == SWT.DOWN) {
			rc = -rc;
		}
		return rc;
	}

}
