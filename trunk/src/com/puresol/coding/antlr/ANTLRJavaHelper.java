package com.puresol.coding.antlr;

import org.apache.log4j.Logger;

public class ANTLRJavaHelper {

	private static final Logger logger = Logger
			.getLogger(ANTLRJavaHelper.class);

	private int slocCount = 0;
	private String packageName = "unknown";
	private int blockLayer = 0;

	public ANTLRJavaHelper() {
	}

	public int getSlocCount() {
		return slocCount;
	}

	public void setSlocCount(int slocCount) {
		this.slocCount = slocCount;
	}

	public void incSlocCount() {
		slocCount++;
		logger.debug("sloc " + slocCount);
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
		logger.debug("package " + packageName);
	}

	public void addImport(String importName) {
		logger.debug("import " + importName);
	}

	public void addBlockBegin() {
		blockLayer++;
		logger
				.debug("block begin: " + blockLayer + " (line " + slocCount
						+ ")");
	}

	public void addBlockEnd() {
		blockLayer--;
		logger
				.debug("block begin: " + blockLayer + " (line " + slocCount
						+ ")");
	}
}
