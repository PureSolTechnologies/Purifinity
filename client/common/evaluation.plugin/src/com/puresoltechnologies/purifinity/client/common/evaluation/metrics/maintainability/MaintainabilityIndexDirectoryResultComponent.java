package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.maintainability;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexFileResult;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexFileResults;

public class MaintainabilityIndexDirectoryResultComponent extends Composite {

	public MaintainabilityIndexDirectoryResultComponent(Composite parent,
			int style, AnalysisRun analysisRun, HashIdFileTree directory) {
		super(parent, style);

		setLayout(new FillLayout());

		EvaluatorStore evaluatorStore = EvaluatorStoreFactory.getFactory()
				.createInstance(MaintainabilityIndexEvaluator.class);

		Map<Double, String> maintainability = new HashMap<Double, String>();
		for (HashIdFileTree child : directory.getChildren()) {
			if (child.isFile()) {
				MaintainabilityIndexFileResults childResults = (MaintainabilityIndexFileResults) evaluatorStore
						.readFileResults(child.getHashId());
				for (MaintainabilityIndexFileResult result : childResults
						.getResults()) {
					if (result.getCodeRangeType() == CodeRangeType.FILE) {
						maintainability.put(result
								.getMaintainabilityIndexResult().getMI(), child
								.getName());
					}
				}
			}
		}
	}

}
