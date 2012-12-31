package com.puresol.coding.client.evaluation.cocomo;

import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.metrics.cocomo.CoCoMoEvaluator;
import com.puresol.coding.metrics.cocomo.CoCoMoValueSet;

public class CoCoMoFileResultComponent extends CoCoMoResultComponent {

    public CoCoMoFileResultComponent(Composite parent, int style,
	    AnalyzedCode analyzedSourceCode) {
	super(parent, style);

	EvaluatorStore evaluatorStore = AbstractEvaluator
		.createEvaluatorStore(CoCoMoEvaluator.class);

	CoCoMoValueSet fileResults = (CoCoMoValueSet) evaluatorStore
		.readFileResults(analyzedSourceCode.getHashId());

	setResults(fileResults);
    }
}
