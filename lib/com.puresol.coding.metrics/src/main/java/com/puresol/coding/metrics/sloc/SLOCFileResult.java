package com.puresol.coding.metrics.sloc;

import java.util.HashMap;

import com.puresol.coding.evaluation.api.FileResult;
import com.puresol.coding.quality.api.SourceCodeQuality;

public class SLOCFileResult extends HashMap<String, SourceCodeQuality>
	implements FileResult {

    private static final long serialVersionUID = -6886659380901480321L;

}
