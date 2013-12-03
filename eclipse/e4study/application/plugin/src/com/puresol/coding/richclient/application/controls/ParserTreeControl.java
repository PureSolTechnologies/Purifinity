package com.puresol.coding.richclient.application.controls;

import java.awt.BorderLayout;
import java.io.IOException;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;

import com.puresoltechnologies.coding.analysis.api.AnalysisRun;
import com.puresoltechnologies.coding.analysis.api.AnalyzedCode;
import com.puresoltechnologies.coding.analysis.api.CodeAnalysis;
import com.puresoltechnologies.coding.analysis.api.CodeStore;
import com.puresoltechnologies.coding.analysis.api.CodeStoreException;
import com.puresoltechnologies.coding.analysis.api.CodeStoreFactory;
import com.puresoltechnologies.coding.richclient.application.content.ParserTreeContentProvider;
import com.puresoltechnologies.coding.richclient.application.content.ParserTreeLabelProvider;

/**
 * This is a simple text element which show a text file.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ParserTreeControl extends Composite {

	private final CodeStore codeStore = CodeStoreFactory.getFactory()
			.getInstance();

	private final Label lblNewLabel;
	private final Tree tree;
	private final TreeViewer treeViewer;

	public ParserTreeControl(Composite parent) {
		super(parent, SWT.NONE);

		setLayout(new GridLayout());
		lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setLayoutData(BorderLayout.NORTH);

		tree = new Tree(this, SWT.BORDER);
		tree.setLayoutData(BorderLayout.CENTER);
		treeViewer = new TreeViewer(tree);
		treeViewer.setContentProvider(new ParserTreeContentProvider());
		treeViewer.setLabelProvider(new ParserTreeLabelProvider());
	}

	/**
	 * This method sets a new file and updates the content.
	 * 
	 * @param file
	 * @throws IOException
	 * @throws FileStoreException
	 */
	public void setContentAndUpdateContent(AnalyzedCode analyzedCode,
			AnalysisRun analysisRun) throws IOException, CodeStoreException {
		CodeAnalysis codeAnalysis = codeStore.loadAnalysis(analyzedCode
				.getHashId());
		if (codeAnalysis != null) {
			lblNewLabel.setText(analysisRun.getInformation()
					.getAnalysisInformation().getName()
					+ ": "
					+ analyzedCode.getLocation()
							.getHumanReadableLocationString());
			treeViewer.setInput(codeAnalysis.getParserTree());
		} else {
			lblNewLabel.setText("");
			treeViewer.setInput(null);
		}
	}
}
