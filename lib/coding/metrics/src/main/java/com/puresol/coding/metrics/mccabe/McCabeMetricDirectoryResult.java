package com.puresol.coding.metrics.mccabe;

import com.puresol.coding.evaluation.api.DirectoryResults;

public class McCabeMetricDirectoryResult implements DirectoryResults {

    private static final long serialVersionUID = -5999635113425940171L;

    private final String file;
    private final int vG;

    public McCabeMetricDirectoryResult(String file, int vG) {
	super();
	this.file = file;
	this.vG = vG;
    }

    public String getFile() {
	return file;
    }

    public int getvG() {
	return vG;
    }

}
