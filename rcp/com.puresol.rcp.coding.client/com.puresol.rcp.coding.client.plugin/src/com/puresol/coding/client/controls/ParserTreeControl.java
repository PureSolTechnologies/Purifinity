package com.puresol.coding.client.controls;

import java.io.IOException;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;

import swing2swt.layout.BorderLayout;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeStore;
import com.puresol.coding.analysis.api.CodeStoreException;
import com.puresol.coding.analysis.api.CodeStoreFactory;
import com.puresol.coding.client.content.ParserTreeContentProvider;
import com.puresol.coding.client.content.ParserTreeLabelProvider;

/**
 * This is a simple text element which show a text file.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ParserTreeControl extends Composite {

    private final CodeStore codeStore = CodeStoreFactory.getInstance();

    private final Label lblNewLabel;
    private final Tree tree;
    private final TreeViewer treeViewer;

    public ParserTreeControl(Composite parent) {
	super(parent, SWT.NONE);

	setLayout(new BorderLayout(0, 0));
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
