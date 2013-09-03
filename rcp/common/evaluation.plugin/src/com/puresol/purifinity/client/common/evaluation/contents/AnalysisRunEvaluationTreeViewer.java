package com.puresol.purifinity.client.common.evaluation.contents;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Tree;

import com.puresol.purifinity.client.common.analysis.contents.AnalysisRunContentTreeContentProvider;
import com.puresol.purifinity.client.common.analysis.contents.AnalysisRunContentTreeViewer;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;

/**
 * This viewer is used in {@link Tree}s to render the content of an analysis
 * run. The tree is shown as file tree with folders and files. Icons and their
 * decorators are rendered.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisRunEvaluationTreeViewer extends TreeViewer {

	private final AnalysisRunEvaluationTreeLabelProvider labelProvider;

	public AnalysisRunEvaluationTreeViewer(Tree tree) {
		super(tree);
		setSorter(new AnalysisRunContentTreeViewer.AnalysisRunContentTreeSorter());
		setContentProvider(new AnalysisRunContentTreeContentProvider());
		labelProvider = new AnalysisRunEvaluationTreeLabelProvider();
		setLabelProvider(labelProvider);
	}

	public void setInput(AnalysisRun analysisRun) {
		labelProvider.setAnalysisRun(analysisRun);
		super.setInput(analysisRun.getFileTree());
	}
}
