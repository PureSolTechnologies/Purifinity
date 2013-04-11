package com.puresol.coding.client.common.chart;

public class Tuple<TX, TY> {

	private final TX x;
	private final TX y;

	public Tuple(TX x, TX y) {
		super();
		this.x = x;
		this.y = y;
	}

	public TX getX() {
		return x;
	}

	public TX getY() {
		return y;
	}

}
