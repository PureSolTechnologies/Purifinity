package com.puresoltechnologies.purifinity.evaluation.domain.style;

import java.util.List;

public interface FileStyleIssues extends StyleIssues {

    public StyleIssueParameter[] getParameters();

    public List<GenericCodeRangeStyleIssues> getCodeRangeStyleIssues();

}
