package com.puresol.coding.metrics.codedepth;

import java.util.Date;

import com.puresol.coding.evaluation.api.DirectoryResults;
import com.puresol.coding.evaluation.api.EvaluatorResults;
import com.puresol.coding.evaluation.api.FileResults;
import com.puresol.coding.evaluation.api.ProjectResults;
import com.puresol.utils.HashId;

public class CodeDepthEvaluatorResults implements EvaluatorResults {

    private static final long serialVersionUID = -7117247746373097938L;

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
