package com.puresoltechnologies.purifinity.evaluation.domain.architecture;

import java.util.List;

public interface FileArchitectureIssues extends ArchitectureIssues {

    public ArchitectureIssueParameter[] getParameters();

    public List<CodeRangeArchitectureIssues> getCodeRangeDesignIssues();

}
