package com.puresol.coding.metrics.mccabe;

import java.util.HashMap;

import com.puresol.coding.evaluation.api.FileResult;
import com.puresol.coding.quality.api.SourceCodeQuality;

public class McCabeMetricFileResult extends HashMap<String, SourceCodeQuality>
	implements FileResult {

    private static final long serialVersionUID = -5992363758018121695L;

}
