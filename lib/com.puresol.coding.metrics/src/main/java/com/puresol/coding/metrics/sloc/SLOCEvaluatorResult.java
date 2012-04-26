package com.puresol.coding.metrics.sloc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.evaluation.api.DirectoryResult;
import com.puresol.coding.evaluation.api.EvaluatorResults;
import com.puresol.coding.evaluation.api.FileResult;
import com.puresol.coding.evaluation.api.ProjectResult;
import com.puresol.utils.HashId;

public class SLOCEvaluatorResult implements EvaluatorResults {

    private static final long serialVersionUID = 6440199089142251237L;

    private final Map<HashId, SLOCResult> fileQualities = new HashMap<HashId, SLOCResult>();
    private final Map<HashId, SLOCResult> directoryQualities = new HashMap<HashId, SLOCResult>();
    private final Map<HashId, Map<CodeRange, SLOCResult>> codeRangeQualities = new HashMap<HashId, Map<CodeRange, SLOCResult>>();

    private final String evaluatorName;
    private final Date time;
    private final long timeOfRun;

    private SLOCEvaluatorResult(String evaluatorName, Date time, long timeOfRun) {
	super();
	this.evaluatorName = evaluatorName;
	this.time = time;
	this.timeOfRun = timeOfRun;
    }

    public Map<HashId, SLOCResult> getFileQualities() {
	return fileQualities;
    }

    public Map<HashId, SLOCResult> getDirectoryQualities() {
	return directoryQualities;
    }

    public Map<HashId, Map<CodeRange, SLOCResult>> getCodeRangeQualities() {
	return codeRangeQualities;
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
    public FileResult getFileResult(HashId hashId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public DirectoryResult getDirectoryResult(HashId hashId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ProjectResult getProjectResult() {
	// TODO Auto-generated method stub
	return null;
    }

}
