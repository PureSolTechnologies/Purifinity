package com.puresoltechnologies.purifinity.client.common.evaluation.contents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Tree;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.client.common.analysis.contents.AnalysisRunContentTreeContentProvider;
import com.puresoltechnologies.purifinity.client.common.analysis.contents.AnalysisRunContentTreeViewer;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.EvaluatorFactory;

/**
 * This viewer is used in {@link Tree}s to render the content of an analysis
 * run. The tree is shown as file tree with folders and files. Icons and their
 * decorators are rendered.
 * 
 * @author Rick-Rainer Ludwig
 */
public class EvaluationFileTreeViewer extends TreeViewer {

	private final EvaluationFileTreeLabelProvider labelProvider;

	public EvaluationFileTreeViewer(Tree tree) {
		super(tree);
		setSorter(new AnalysisRunContentTreeViewer.AnalysisRunContentTreeSorter());
		setContentProvider(new AnalysisRunContentTreeContentProvider());
		labelProvider = new EvaluationFileTreeLabelProvider();
		setLabelProvider(labelProvider);
	}

	public void setInput(AnalysisRun analysisRun) {
		super.setInput(analysisRun.getFileTree());
		labelProvider.setAnalysisRun(analysisRun);
	}

	public void setEvaluator(EvaluatorFactory evaluator) {
		labelProvider.setEvaluator(evaluator);
		refresh();
	}

	public void setSelection(HashIdFileTree node) {
		List<Object> path = new ArrayList<>();
		do {
			if (node.getParent() != null) {
				path.add(node);
			} else {
				path.add(node.getName());
			}
			node = node.getParent();
		} while (node != null);
		Collections.reverse(path);
		TreeSelection structuredSelection = new TreeSelection(new TreePath(
				path.toArray()));
		setSelection(structuredSelection);
	}
}
