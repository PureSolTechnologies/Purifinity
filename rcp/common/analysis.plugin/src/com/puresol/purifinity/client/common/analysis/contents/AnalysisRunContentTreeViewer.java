package com.puresol.purifinity.client.common.analysis.contents;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.widgets.Tree;

import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;

/**
 * This viewer is used in {@link Tree}s to render the content of an analysis
 * run. The tree is shown as file tree with folders and files. Icons and their
 * decorators are rendered.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisRunContentTreeViewer extends TreeViewer {

	public static final class AnalysisRunContentTreeSorter extends ViewerSorter {
		@Override
		public int compare(Viewer viewer, Object e1, Object e2) {
			String left = "";
			String right = "";
			String dirFileLeft = "dir";
			String dirFileRight = "dir";
			if (String.class.isAssignableFrom(e1.getClass())) {
				left = (String) e1;
			} else {
				HashIdFileTree leftHashIdFileTree = (HashIdFileTree) e1;
				left = leftHashIdFileTree.getName().toLowerCase();
				if (leftHashIdFileTree.isFile()) {
					dirFileLeft = "file";
				}
			}
			if (String.class.isAssignableFrom(e2.getClass())) {
				right = (String) e2;
			} else {
				HashIdFileTree rightHashIdFileTree = (HashIdFileTree) e2;
				right = rightHashIdFileTree.getName().toLowerCase();
				if (rightHashIdFileTree.isFile()) {
					dirFileRight = "file";
				}
			}
			if (dirFileRight.equals(dirFileLeft)) {
				return left.compareTo(right);
			}
			return dirFileLeft.compareTo(dirFileRight);
		}
	}

	private final AnalysisRunContentTreeLabelProvider labelProvider;

	public AnalysisRunContentTreeViewer(Tree tree) {
		super(tree);
		setSorter(new AnalysisRunContentTreeSorter());
		setContentProvider(new AnalysisRunContentTreeContentProvider());
		labelProvider = new AnalysisRunContentTreeLabelProvider();
		setLabelProvider(labelProvider);
	}

	public void setInput(AnalysisRun analysisRun) {
		labelProvider.setAnalysisRun(analysisRun);
		super.setInput(analysisRun.getFileTree());
	}
}