package com.puresoltechnologies.commons.misc;

public enum FileSizes {

    B("B"), KB("kB"), MB("MB"), GB("GB"), TB("TB"), PB("PB"), EB("EB"), ZB("ZB"), YB(
	    "YB");

    private final String unit;

    private FileSizes(String unit) {
	this.unit = unit;
    }

    @Override
    public String toString() {
	return unit;
    }
}
