package com.puresol.coding.metrics.codedepth;

import java.util.HashMap;

import com.puresol.coding.evaluation.api.FileResult;
import com.puresol.coding.quality.api.SourceCodeQuality;

public class CodeDepthFileResult extends HashMap<String, SourceCodeQuality>
	implements FileResult {

    private static final long serialVersionUID = 8283322540485150992L;

}
