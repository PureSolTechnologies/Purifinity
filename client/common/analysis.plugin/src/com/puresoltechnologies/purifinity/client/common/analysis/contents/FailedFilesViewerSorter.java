package com.puresoltechnologies.purifinity.client.common.analysis.contents;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.client.common.ui.contents.AbstractViewerSorter;

public class FailedFilesViewerSorter extends AbstractViewerSorter {

	private final FailedFilesTableViewer failedFilesTableViewer;

	public FailedFilesViewerSorter(FailedFilesTableViewer failedFilesTableViewer) {
		this.failedFilesTableViewer = failedFilesTableViewer;
	}

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		AnalysisInformation code1 = (AnalysisInformation) e1;
		AnalysisInformation code2 = (AnalysisInformation) e2;
		int rc = 0;
		switch (getColumn()) {
		case 0:
			AnalysisRun analysisRun = failedFilesTableViewer.getAnalysisRun();
			String name1 = analysisRun.findTreeNode(code1.getHashId())
					.getSourceCodeLocation().getHumanReadableLocationString()
					.toLowerCase();
			String name2 = analysisRun.findTreeNode(code2.getHashId())
					.getSourceCodeLocation().getHumanReadableLocationString()
					.toLowerCase();
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
