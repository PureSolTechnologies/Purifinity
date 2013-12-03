package com.puresol.coding.richclient.application.evaluation.cocomo;

import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.api.evaluation.EvaluatorStore;

public class CoCoMoDirectoryResultComponent extends CoCoMoResultComponent {

	public CoCoMoDirectoryResultComponent(Composite parent, int style,
			HashIdFileTree directory) {
		super(parent, style);

		EvaluatorStore evaluatorStore = AbstractEvaluator
				.createEvaluatorStore(CoCoMoEvaluator.class);

		CoCoMoValueSet directoryResults = (CoCoMoValueSet) evaluatorStore
				.readDirectoryResults(directory.getHashId());

		setResults(directoryResults);
	}

}
