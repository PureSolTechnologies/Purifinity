package com.puresoltechnologies.purifinity.client.common.evaluation.contents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Tree;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.client.common.analysis.contents.AnalysisRunContentTreeContentProvider;
import com.puresoltechnologies.purifinity.client.common.analysis.contents.AnalysisRunContentTreeViewer;
import com.puresoltechnologies.purifinity.client.common.server.EvaluatorFactory;

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
		if (analysisRun != null) {
			super.setInput(analysisRun.getFileTree());
		} else {
			super.setInput(null);
		}
		labelProvider.setAnalysisRun(analysisRun);
	}

	public void setEvaluator(EvaluatorFactory evaluator) {
		labelProvider.setEvaluator(evaluator);
		refresh();
	}

	public void setSelection(AnalysisFileTree node) {
		List<Object> path = new ArrayList<>();
		while (node != null) {
			if (node.getParent() != null) {
				path.add(node);
			} else {
				path.add(node.getName());
			}
			node = node.getParent();
		}
		Collections.reverse(path);
		TreeSelection structuredSelection = new TreeSelection(new TreePath(
				path.toArray()));
		setSelection(structuredSelection);
	}
}
