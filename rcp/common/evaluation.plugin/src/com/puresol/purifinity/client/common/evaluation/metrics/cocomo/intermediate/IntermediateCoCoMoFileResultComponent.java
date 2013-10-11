package com.puresol.purifinity.client.common.evaluation.metrics.cocomo.intermediate;

import org.eclipse.swt.widgets.Composite;

import com.puresol.purifinity.coding.analysis.api.AnalyzedCode;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluator;
import com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoResults;

public class IntermediateCoCoMoFileResultComponent extends
		IntermediateCoCoMoResultComponent {

	public IntermediateCoCoMoFileResultComponent(Composite parent,
			AnalyzedCode analyzedSourceCode) {
		super(parent);

		EvaluatorStore evaluatorStore = EvaluatorStoreFactory.getFactory()
				.createInstance(BasicCoCoMoEvaluator.class);

		IntermediateCoCoMoResults fileResults = (IntermediateCoCoMoResults) evaluatorStore
				.readFileResults(analyzedSourceCode.getHashId());

		setResults(fileResults);
	}
}
