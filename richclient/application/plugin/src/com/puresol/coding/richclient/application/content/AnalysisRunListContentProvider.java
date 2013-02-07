package com.puresol.coding.richclient.application.content;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.puresol.coding.analysis.api.AnalysisRunInformation;

/**
 * This content provider is used to show a list of available analysis runs from
 * the analysis store. The input of the list is set as
 * {@link AnalysisRunInformation} of the analysis which runs need to be shown.
 * The information about the runs is taken from the singleton of AnalysisStore
 * which is retrieved via {@link AnalysisStoreFactory}.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisRunListContentProvider implements
		IStructuredContentProvider {

	private final List<AnalysisRunInformation> allRuns = new ArrayList<AnalysisRunInformation>();

	@Override
	public void dispose() {
		// Intentionally left blank
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		allRuns.clear();
		if (newInput != null) {
			@SuppressWarnings("unchecked")
			List<AnalysisRunInformation> runInformation = (List<AnalysisRunInformation>) newInput;
			allRuns.addAll(runInformation);
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (allRuns != null) {
			return allRuns.toArray();
		}
		return new AnalysisRunInformation[0];
	}
}
