package com.puresol.purifinity.client.common.evaluation.metrics.cocomo.basic;

import org.eclipse.swt.widgets.Composite;

import com.puresol.purifinity.coding.analysis.api.AnalyzedCode;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluator;
import com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoResults;

public class BasicCoCoMoFileResultComponent extends BasicCoCoMoResultComponent {

	public BasicCoCoMoFileResultComponent(Composite parent,
			AnalyzedCode analyzedSourceCode) {
		super(parent);

		EvaluatorStore evaluatorStore = EvaluatorStoreFactory.getFactory()
				.createInstance(BasicCoCoMoEvaluator.class);

		BasicCoCoMoResults fileResults = (BasicCoCoMoResults) evaluatorStore
				.readFileResults(analyzedSourceCode.getHashId());

		setResults(fileResults);
	}
}
