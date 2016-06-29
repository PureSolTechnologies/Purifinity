package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;

import javax.batch.api.Batchlet;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.server.database.hadoop.utils.HadoopClientHelper;

/**
 * This batchlet runs the configured pre-analysis script, collects the stdout
 * and stderr and waits for the finalization. If no pre-analysis script is
 * configured, this batchlet is skipped.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
@Named("RunPreAnalysisScriptBatchlet")
public class RunPreAnalysisScriptBatchlet implements Batchlet {

    private int BUFFER_SIZE = 8192;

    @Inject
    private JobContext jobContext;

    @Inject
    private FileSystem fileSystem;

    @Override
    public String process() throws Exception {
	AnalysisJobContext analysisJobContext = (AnalysisJobContext) jobContext.getTransientUserData();
	AnalysisProject analysisProject = analysisJobContext.getAnalysisProject();
	File preAnalysisScript = analysisProject.getSettings().getPreAnalysisScript();
	if (preAnalysisScript == null) {
	    return AnalysisJobExitString.SKIPPED.get();
	}
	AnalysisRunInformation analysisRunInformation = analysisJobContext.getAnalysisRunInformation();
	Path projectsPath = new Path(HadoopClientHelper.getProjectsPath());
	if (!fileSystem.exists(projectsPath)) {
	    throw new RuntimeException("Could not run pre-analysis script due to missing projects directory in HDFS.");
	}

	String projectId = analysisRunInformation.getProjectId();
	long runId = analysisRunInformation.getRunId();
	String projectRunPathString = HadoopClientHelper.getProjectRunPath(projectId, runId);
	Path projectRunPath = new Path(projectRunPathString);
	if (!fileSystem.exists(projectRunPath)) {
	    fileSystem.mkdirs(projectRunPath);
	}
	ProcessBuilder processBuilder;
	if (preAnalysisScript.isAbsolute()) {
	    processBuilder = new ProcessBuilder(preAnalysisScript.getPath()).redirectErrorStream(true);
	} else {
	    return AnalysisJobExitString.ABORT.get();
	}
	Process process = processBuilder.start();
	Path stdoutPath = new Path(HadoopClientHelper.getPreAnalysisScriptStdoutFile(projectId, runId));
	try (BufferedInputStream stdoutInput = new BufferedInputStream(process.getInputStream(), BUFFER_SIZE);
		BufferedOutputStream stdoutOutput = new BufferedOutputStream(fileSystem.create(stdoutPath),
			BUFFER_SIZE)) {
	    byte[] buffer = new byte[BUFFER_SIZE];
	    while (process.isAlive()) {
		int available = stdoutInput.available();
		if (available > 0) {
		    if (available > BUFFER_SIZE) {
			available = BUFFER_SIZE;
		    }
		    stdoutInput.read(buffer, 0, available);
		    stdoutOutput.write(buffer, 0, available);
		}
	    }
	    int available = stdoutInput.available();
	    while (available > 0) {
		stdoutInput.read(buffer, 0, available);
		stdoutOutput.write(buffer, 0, available);
		available = stdoutInput.available();
	    }
	}
	return AnalysisJobExitString.SUCCESSFUL.get();
    }

    @Override
    public void stop() throws Exception {
	// TODO Auto-generated method stub
    }

}
