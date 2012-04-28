package com.puresol.coding.metrics.normmaint;

import java.util.Date;

import com.puresol.coding.evaluation.api.DirectoryResults;
import com.puresol.coding.evaluation.api.EvaluatorResults;
import com.puresol.coding.evaluation.api.FileResults;
import com.puresol.coding.evaluation.api.ProjectResults;
import com.puresol.utils.HashId;

public class NormalizedMaintainabilityIndexEvaluatorResults implements
	EvaluatorResults {

    /**
     * 
     */
    private static final long serialVersionUID = -1934870257264872806L;

    @Override
    public String getEvaluatorName() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Date getTime() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public long getTimeOfRun() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public FileResults getFileResult(HashId hashId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public DirectoryResults getDirectoryResult(HashId hashId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ProjectResults getProjectResult() {
	// TODO Auto-generated method stub
	return null;
    }

}
