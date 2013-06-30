package com.puresol.purifinity.client.common.evaluation.metrics.cocomo;

import org.eclipse.swt.widgets.Composite;

import com.puresol.purifinity.coding.analysis.api.AnalyzedCode;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.purifinity.coding.metrics.cocomo.CoCoMoEvaluator;
import com.puresol.purifinity.coding.metrics.cocomo.CoCoMoResults;

public class CoCoMoFileResultComponent extends CoCoMoResultComponent {

	public CoCoMoFileResultComponent(Composite parent, int style,
			AnalyzedCode analyzedSourceCode) {
		super(parent, style);

		EvaluatorStore evaluatorStore = EvaluatorStoreFactory.getFactory()
				.createInstance(CoCoMoEvaluator.class);

		CoCoMoResults fileResults = (CoCoMoResults) evaluatorStore
				.readFileResults(analyzedSourceCode.getHashId());

		setResults(fileResults);
	}
}
