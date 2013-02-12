package com.puresol.coding.metrics.mccabe;

import com.puresol.coding.evaluation.api.DirectoryResults;
import com.puresol.uhura.source.CodeLocation;

public class McCabeMetricDirectoryResult implements DirectoryResults {

	private static final long serialVersionUID = -5999635113425940171L;

	private final CodeLocation sourceCodeLocation;
	private final int vG;

	public McCabeMetricDirectoryResult(CodeLocation sourceCodeLocation, int vG) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.vG = vG;
	}

	public CodeLocation getSourceCodeLocation() {
		return sourceCodeLocation;
	}

	public int getvG() {
		return vG;
	}

}
