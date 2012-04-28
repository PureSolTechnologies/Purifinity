package com.puresol.coding.client.evaluation.sloc;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.evaluation.api.EvaluationResultsStore;
import com.puresol.coding.evaluation.api.EvaluationResultsStoreFactory;
import com.puresol.coding.metrics.sloc.SLOCEvaluator;
import com.puresol.coding.metrics.sloc.SLOCFileResult;

public class SLOCFileResultComponent extends Composite {

    private static final EvaluationResultsStore resultStore = EvaluationResultsStoreFactory
	    .getInstance();

    private final AnalysisRun analysisRun;
    private final AnalyzedFile analyzedFile;

    public SLOCFileResultComponent(Composite parent, int style,
	    AnalysisRun analysisRun, AnalyzedFile analyzedFile) {
	super(parent, style);

	SLOCEvaluator evaluator = new SLOCEvaluator(analysisRun);

	this.analysisRun = analysisRun;
	this.analyzedFile = analyzedFile;

	setLayout(new RowLayout());
	Text text = new Text(this, SWT.BORDER);

	SLOCFileResult fileResults = (SLOCFileResult) resultStore
		.createEvaluatorStore(SLOCEvaluator.class).readFileResults(
			analyzedFile.getHashId());
	text.setText(fileResults.toString());
    }

}
