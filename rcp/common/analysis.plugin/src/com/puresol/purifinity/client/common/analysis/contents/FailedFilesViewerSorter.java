package com.puresol.purifinity.client.common.analysis.contents;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;

import com.puresol.purifinity.client.common.ui.contents.AbstractViewerSorter;
import com.puresol.purifinity.coding.analysis.api.AnalyzedCode;

public class FailedFilesViewerSorter extends AbstractViewerSorter {

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		AnalyzedCode code1 = (AnalyzedCode) e1;
		AnalyzedCode code2 = (AnalyzedCode) e2;
		int rc = 0;
		switch (getColumn()) {
		case 0:
			String name1 = code1.getSourceLocation()
					.getHumanReadableLocationString().toLowerCase();
			String name2 = code2.getSourceLocation()
					.getHumanReadableLocationString().toLowerCase();
			rc = name1.compareTo(name2);
			break;
		case 1:
			rc = code1.getStartTime().compareTo(code2.getStartTime());
			break;
		case 2:
			String message1 = code1.getMessage().toLowerCase();
			String message2 = code2.getMessage().toLowerCase();
			rc = message1.compareTo(message2);
			break;
		}
		if (getDirection() == SWT.DOWN) {
			rc = -rc;
		}
		return rc;
	}

}
