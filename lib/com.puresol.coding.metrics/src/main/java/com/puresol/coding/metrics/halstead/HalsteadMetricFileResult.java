package com.puresol.coding.metrics.halstead;

import java.util.HashMap;

import com.puresol.coding.evaluation.api.FileResults;
import com.puresol.coding.quality.api.SourceCodeQuality;

public class HalsteadMetricFileResult extends
	HashMap<String, SourceCodeQuality> implements FileResults {

    private static final long serialVersionUID = -5970030495863471269L;

}
