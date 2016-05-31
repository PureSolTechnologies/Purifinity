package com.puresoltechnologies.purifinity.evaluation.domain.defects;

import java.util.List;

public interface FileDefects extends Defects {

    public DefectParameter[] getParameters();

    public List<CodeRangeDefects> getCodeRangeDefects();

}
