package com.puresoltechnologies.purifinity.client.common.analysis.contents;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.client.common.ui.contents.AbstractViewerSorter;

public class AnalyzedFilesViewerSorter extends AbstractViewerSorter {

	private final AnalyzedFilesTableViewer analyzedFilesTableViewer;

	public AnalyzedFilesViewerSorter(
			AnalyzedFilesTableViewer analyzedFilesTableViewer) {
		this.analyzedFilesTableViewer = analyzedFilesTableViewer;
	}

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		AnalysisInformation code1 = (AnalysisInformation) e1;
		AnalysisInformation code2 = (AnalysisInformation) e2;
		int rc = 0;
		switch (getColumn()) {
		case 0:
			AnalysisRun analysisRun = analyzedFilesTableViewer.getAnalysisRun();
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
			String language1 = code1.getLanguageName()
					+ code1.getLanguageVersion();
			String language2 = code2.getLanguageName()
					+ code2.getLanguageVersion();
			rc = language1.compareTo(language2);
			break;
		case 3:
			rc = Long.valueOf(code1.getDuration()).compareTo(
					Long.valueOf(code2.getDuration()));
			break;
		}
		if (getDirection() == SWT.DOWN) {
			rc = -rc;
		}
		return rc;
	}

}
