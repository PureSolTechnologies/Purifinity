package com.puresol.coding.client.application.controls;

import java.io.IOException;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeStore;
import com.puresol.coding.analysis.api.CodeStoreException;
import com.puresol.coding.analysis.api.CodeStoreFactory;
import com.puresol.coding.client.application.content.ParserTreeContentProvider;
import com.puresol.coding.client.application.content.ParserTreeLabelProvider;

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

	lblNewLabel = new Label(this, SWT.NONE);

	tree = new Tree(this, SWT.BORDER);
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
		    .getAnalysisProject().getSettings().getName()
		    + ": "
		    + analyzedCode.getSourceLocation()
			    .getHumanReadableLocationString());
	    treeViewer.setInput(codeAnalysis.getParserTree());
	} else {
	    lblNewLabel.setText("");
	    treeViewer.setInput(null);
	}
    }
}
