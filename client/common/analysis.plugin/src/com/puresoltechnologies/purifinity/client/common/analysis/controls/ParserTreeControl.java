package com.puresoltechnologies.purifinity.client.common.analysis.controls;

import java.io.IOException;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzedCode;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalysis;
import com.puresoltechnologies.purifinity.client.common.analysis.contents.UniversalSyntaxTreeContentProvider;
import com.puresoltechnologies.purifinity.client.common.analysis.contents.UniversalSyntaxTreeLabelProvider;
import com.puresoltechnologies.purifinity.framework.store.api.FileStore;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreFactory;

/**
 * This is a simple text element which show a text file.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ParserTreeControl extends Composite {

	private final FileStore codeStore = FileStoreFactory.getFactory()
			.getInstance();

	private final Label lblNewLabel;
	private final Tree tree;
	private final TreeViewer treeViewer;

	public ParserTreeControl(Composite parent) {
		super(parent, SWT.NONE);
		setLayout(new FormLayout());

		lblNewLabel = new Label(this, SWT.BORDER);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 10);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		fd_lblNewLabel.right = new FormAttachment(100, -10);
		fd_lblNewLabel.bottom = new FormAttachment(0, 28);
		lblNewLabel.setLayoutData(fd_lblNewLabel);

		tree = new Tree(this, SWT.BORDER);
		FormData fd_tree = new FormData();
		fd_tree.top = new FormAttachment(lblNewLabel, 6);
		fd_tree.bottom = new FormAttachment(100, -10);
		fd_tree.left = new FormAttachment(0, 10);
		fd_tree.right = new FormAttachment(100, -10);
		tree.setLayoutData(fd_tree);
		treeViewer = new TreeViewer(tree);
		treeViewer.setContentProvider(new UniversalSyntaxTreeContentProvider());
		treeViewer.setLabelProvider(new UniversalSyntaxTreeLabelProvider());
	}

	/**
	 * This method sets a new file and updates the content.
	 * 
	 * @param file
	 * @throws IOException
	 * @throws FileStoreException
	 */
	public void setContentAndUpdateContent(AnalyzedCode analyzedCode,
			AnalysisRun analysisRun) throws IOException, FileStoreException {
		CodeAnalysis codeAnalysis = codeStore.loadAnalysis(analyzedCode
				.getHashId());
		if (codeAnalysis != null) {
			lblNewLabel.setText(analysisRun.getInformation()
					.getAnalysisProject().getSettings().getName()
					+ ": "
					+ analyzedCode.getSourceLocation()
							.getHumanReadableLocationString());
			treeViewer.setInput(codeAnalysis.getUniversalSyntaxTree());
		} else {
			lblNewLabel.setText("");
			treeViewer.setInput(null);
		}
	}
}
