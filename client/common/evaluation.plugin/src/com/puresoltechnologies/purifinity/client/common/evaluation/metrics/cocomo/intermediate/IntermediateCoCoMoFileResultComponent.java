package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.cocomo.intermediate;

import org.eclipse.swt.widgets.Composite;

import com.puresoltechnologies.purifinity.analysis.api.AnalyzedCode;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.basic.BasicCoCoMoEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.intermediate.IntermediateCoCoMoResults;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStoreFactory;

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
