package com.puresoltechnologies.purifinity.client.common.analysis.contents;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;

/**
 * This content provider is used to show a list of available analyzes from the
 * analysis store. The input of the list is not needed to be set. The
 * {@link AnalysisStoreFactory} is used to get the singleton instance of the
 * store to provide the content.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisListContentProvider implements IStructuredContentProvider {

	private final List<AnalysisProject> allAnalyzes = new ArrayList<AnalysisProject>();

	@Override
	public void dispose() {
		// Intentionally left blank
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		allAnalyzes.clear();
		if (newInput != null) {
			@SuppressWarnings("unchecked")
			List<AnalysisProject> allAnalysisInformation = (List<AnalysisProject>) newInput;
			allAnalyzes.addAll(allAnalysisInformation);
		}
	}

	@Override
	public AnalysisProject[] getElements(Object inputElement) {
		return allAnalyzes.toArray(new AnalysisProject[allAnalyzes.size()]);
	}
}
