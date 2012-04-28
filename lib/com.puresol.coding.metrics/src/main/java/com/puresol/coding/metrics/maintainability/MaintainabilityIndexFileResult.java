package com.puresol.coding.metrics.maintainability;

import java.util.HashMap;

import com.puresol.coding.evaluation.api.FileResults;
import com.puresol.coding.quality.api.SourceCodeQuality;

public class MaintainabilityIndexFileResult extends
	HashMap<String, SourceCodeQuality> implements FileResults {

    private static final long serialVersionUID = -5901342878584699006L;

}
