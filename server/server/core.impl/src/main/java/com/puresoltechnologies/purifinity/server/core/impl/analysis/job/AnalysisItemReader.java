package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.io.Serializable;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.context.JobContext;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.server.common.job.PersistentStepUserData;

@Named("AnalysisItemReader")
public class AnalysisItemReader extends AbstractItemReader {

    @Inject
    private Logger logger;

    @Inject
    private JobContext jobContext;

    @Inject
    private StepContext stepContext;

    private AnalysisCheckpoint checkpoint;

    @Override
    public void open(Serializable checkpoint) throws Exception {
	logger.info("Item reader started for job " + jobContext.getJobName() + "...");
	if (checkpoint != null) {
	    this.checkpoint = (AnalysisCheckpoint) checkpoint;
	} else {
	    AnalysisJobContext analysisJobContext = (AnalysisJobContext) jobContext.getTransientUserData();
	    this.checkpoint = new AnalysisCheckpoint(analysisJobContext.getStoredFiles());
	}
	stepContext.setPersistentUserData(new PersistentStepUserData("File Analysis",
		"The files in projects are analyzed.", this.checkpoint.getTotalItemCount()));
    }

    @Override
    public Object readItem() throws Exception {
	return checkpoint.getNext();
    }

    public AnalysisCheckpoint getCheckpoint() {
	return checkpoint;
    }

    @Override
    public void close() throws Exception {
	logger.info("Item reader closed for job " + jobContext.getJobName() + ".");
    }
}
