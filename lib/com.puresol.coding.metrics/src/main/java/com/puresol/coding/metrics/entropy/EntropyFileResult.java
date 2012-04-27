package com.puresol.coding.metrics.entropy;

import java.util.HashMap;

import com.puresol.coding.evaluation.api.FileResult;
import com.puresol.coding.quality.api.SourceCodeQuality;

public class EntropyFileResult extends HashMap<String, SourceCodeQuality>
	implements FileResult {

    private static final long serialVersionUID = 5340666929012251208L;

}
