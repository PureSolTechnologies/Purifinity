package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.io.Serializable;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStore;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManager;

@Named("EvaluationItemReader")
public class EvaluationItemReader extends AbstractItemReader {

	@Inject
	private Logger logger;

	@Inject
	private AnalysisStore analysisStore;

	@Inject
	private EvaluatorServiceManager evaluatorPluginService;

	@Inject
	private JobContext jobContext;

	@Override
	public void open(Serializable checkpoint) throws Exception {
		AnalysisJobContext analysisJobContext = (AnalysisJobContext) jobContext
				.getTransientUserData();

		AnalysisRunInformation analysisRunInformation = analysisJobContext
				.getAnalysisRunInformation();

		AnalysisFileTree analysisFileTree = analysisStore.readAnalysisFileTree(
				analysisRunInformation.getProjectId(),
				analysisRunInformation.getRunId());

		AnalysisRun analysisRun = new AnalysisRun(analysisRunInformation,
				analysisFileTree);

	}

	@Override
	public Object readItem() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
