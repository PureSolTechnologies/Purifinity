package com.puresol.coding.metrics.normmaint;

import java.util.HashMap;

import com.puresol.coding.evaluation.api.FileResults;
import com.puresol.coding.quality.api.SourceCodeQuality;

public class NormalizedMaintainabilityIndexFileResult extends
	HashMap<String, SourceCodeQuality> implements FileResults {

    private static final long serialVersionUID = 7667134885288322378L;

}
