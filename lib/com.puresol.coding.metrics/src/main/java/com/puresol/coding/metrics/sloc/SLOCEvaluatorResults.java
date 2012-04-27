package com.puresol.coding.metrics.sloc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.puresol.coding.evaluation.api.EvaluatorResults;
import com.puresol.utils.HashId;

public class SLOCEvaluatorResults implements EvaluatorResults {

    private static final long serialVersionUID = 6440199089142251237L;

    private final Map<HashId, SLOCFileResult> fileResults = new HashMap<HashId, SLOCFileResult>();
    private final Map<HashId, SLOCDirectoryResult> directoryResults = new HashMap<HashId, SLOCDirectoryResult>();
    private SLOCProjectResult projectResult;

    private final String evaluatorName;
    private final Date time;
    private final long timeOfRun;

    private SLOCEvaluatorResults(String evaluatorName, Date time, long timeOfRun) {
	super();
	this.evaluatorName = evaluatorName;
	this.time = time;
	this.timeOfRun = timeOfRun;
    }

    void addFileQuality(HashId hashId, SLOCFileResult fileResult) {
	fileResults.put(hashId, fileResult);
    }

    void addDirectoryQuality(HashId hashId, SLOCDirectoryResult directoryResult) {
	directoryResults.put(hashId, directoryResult);
    }

    void setProjectResult(SLOCProjectResult projectResult) {
	this.projectResult = projectResult;
    }

    @Override
    public String getEvaluatorName() {
	return evaluatorName;
    }

    @Override
    public Date getTime() {
	return time;
    }

    @Override
    public long getTimeOfRun() {
	return timeOfRun;
    }

    @Override
    public SLOCFileResult getFileResult(HashId hashId) {
	return fileResults.get(hashId);
    }

    @Override
    public SLOCDirectoryResult getDirectoryResult(HashId hashId) {
	return directoryResults.get(hashId);
    }

    @Override
    public SLOCProjectResult getProjectResult() {
	return projectResult;
    }

}
