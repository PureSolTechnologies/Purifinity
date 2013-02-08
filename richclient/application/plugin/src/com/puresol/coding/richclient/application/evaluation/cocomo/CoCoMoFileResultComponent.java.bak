package com.puresol.coding.richclient.application.evaluation.cocomo;

import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.analysis.api.evaluation.EvaluatorStore;

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
