package com.puresol.coding.metrics.cocomo;

import java.util.Date;

import com.puresol.coding.evaluation.api.DirectoryResult;
import com.puresol.coding.evaluation.api.EvaluatorResults;
import com.puresol.coding.evaluation.api.FileResult;
import com.puresol.coding.evaluation.api.ProjectResult;
import com.puresol.utils.HashId;

public class CoCoMoEvaluatorResults implements EvaluatorResults {

    private static final long serialVersionUID = -5532684337311126787L;

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
