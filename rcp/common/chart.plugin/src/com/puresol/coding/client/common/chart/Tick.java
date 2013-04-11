package com.puresol.coding.client.common.chart;

public class Tick<T> {

	private final TickType type;
	private final T value;
	private final double position;
	private final String label;

	public Tick(TickType type, T value, double position, String label) {
		super();
		this.type = type;
		this.value = value;
		this.position = position;
		this.label = label;
	}

	public TickType getType() {
		return type;
	}

	public T getValue() {
		return value;
	}

	public double getPosition() {
		return position;
	}

	public String getLabel() {
		return label;
	}

}
