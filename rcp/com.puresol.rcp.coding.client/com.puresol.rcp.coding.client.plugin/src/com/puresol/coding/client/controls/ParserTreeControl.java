package com.puresol.coding.client.controls;

import java.io.IOException;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;

import swing2swt.layout.BorderLayout;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisStoreException;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.content.ParserTreeContentProvider;
import com.puresol.coding.client.content.ParserTreeLabelProvider;

/**
 * This is a simple text element which show a text file.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ParserTreeControl extends Composite {

    private static final ILog logger = Activator.getDefault().getLog();

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
     */
    public void setContentAndUpdateContent(AnalyzedFile analyzedFile,
	    Analysis analysis) throws IOException {
	try {
	    FileAnalysis fileAnalysis = analysis.loadLastAnalysisRun()
		    .getAnalysis(analyzedFile.getHashId());
	    if (fileAnalysis != null) {
		lblNewLabel.setText(analysis.getInformation().getName() + ": "
			+ analyzedFile.getFile());
		treeViewer.setInput(fileAnalysis.getParserTree());
	    } else {
		lblNewLabel.setText("");
		treeViewer.setInput(null);
	    }
	} catch (AnalysisStoreException e) {
	    logger.log(new Status(Status.ERROR, ParserTreeControl.class
		    .getName(), "Can not read analysis store!", e));
	}
    }
}
