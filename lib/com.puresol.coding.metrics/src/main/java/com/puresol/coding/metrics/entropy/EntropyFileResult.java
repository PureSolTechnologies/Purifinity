package com.puresol.coding.metrics.entropy;

import java.util.HashMap;

import com.puresol.coding.evaluation.api.FileResults;
import com.puresol.coding.quality.api.SourceCodeQuality;

public class EntropyFileResult extends HashMap<String, SourceCodeQuality>
	implements FileResults {

    private static final long serialVersionUID = 5340666929012251208L;

}
