package com.puresol.coding.metrics.sloc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.puresol.coding.evaluation.api.EvaluatorResults;
import com.puresol.utils.HashId;

public class SLOCEvaluatorResults implements EvaluatorResults {

    private static final long serialVersionUID = 6440199089142251237L;

    private final Map<HashId, SLOCFileResults> fileResults = new HashMap<HashId, SLOCFileResults>();
    private final Map<HashId, SLOCDirectoryResults> directoryResults = new HashMap<HashId, SLOCDirectoryResults>();
    private SLOCProjectResults projectResult;

    private final String evaluatorName;
    private final Date time;
    private final long timeOfRun;

    private SLOCEvaluatorResults(String evaluatorName, Date time, long timeOfRun) {
	super();
	this.evaluatorName = evaluatorName;
	this.time = time;
	this.timeOfRun = timeOfRun;
    }

    void addFileQuality(HashId hashId, SLOCFileResults fileResult) {
	fileResults.put(hashId, fileResult);
    }

    void addDirectoryQuality(HashId hashId, SLOCDirectoryResults directoryResult) {
	directoryResults.put(hashId, directoryResult);
    }

    void setProjectResult(SLOCProjectResults projectResult) {
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
    public SLOCFileResults getFileResult(HashId hashId) {
	return fileResults.get(hashId);
    }

    @Override
    public SLOCDirectoryResults getDirectoryResult(HashId hashId) {
	return directoryResults.get(hashId);
    }

    @Override
    public SLOCProjectResults getProjectResult() {
	return projectResult;
    }

}
