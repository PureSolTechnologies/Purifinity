package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.maintainability;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Status;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.Bundle;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.client.common.evaluation.Activator;
import com.puresoltechnologies.purifinity.client.common.server.connectors.MaintainabilityIndexEvaluatorConnector;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.maintainability.MaintainabilityIndexFileResult;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.maintainability.MaintainabilityIndexFileResults;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;

public class MaintainabilityIndexDirectoryResultComponent extends Composite {

	public MaintainabilityIndexDirectoryResultComponent(Composite parent,
			int style, AnalysisRun analysisRun, AnalysisFileTree directory) {
		super(parent, style);
		try {
			setLayout(new FillLayout());

			EvaluatorStore evaluatorStore = MaintainabilityIndexEvaluatorConnector
					.getStore();

			Map<Double, String> maintainability = new HashMap<Double, String>();
			for (AnalysisFileTree child : directory.getChildren()) {
				if (child.isFile()) {
					MaintainabilityIndexFileResults childResults = (MaintainabilityIndexFileResults) evaluatorStore
							.readFileResults(child.getHashId());
					for (MaintainabilityIndexFileResult result : childResults
							.getResults()) {
						if (result.getCodeRangeType() == CodeRangeType.FILE) {
							maintainability.put(result
									.getMaintainabilityIndexResult().getMI(),
									child.getName());
						}
					}
				}
			}
		} catch (EvaluationStoreException e) {
			Activator activator = Activator.getDefault();
			Bundle bundle = activator.getBundle();
			activator.getLog().log(
					new Status(Status.ERROR, bundle.getSymbolicName(),
							"Could not read results.", e));
		}
	}

}
