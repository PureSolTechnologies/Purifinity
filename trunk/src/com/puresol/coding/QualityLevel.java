package com.puresol.coding;

public enum QualityLevel {
	LOW(1), MEDIUM(2), HIGH(3);

	private int level;

	private QualityLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}
}
