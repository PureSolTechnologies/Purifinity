package com.puresol.coding.client.common.chart.math;

public class ParetoValue<C, V extends Comparable<V>> implements
		Comparable<ParetoValue<C, V>> {

	private final C category;
	private final V value;

	public ParetoValue(C category, V value) {
		super();
		this.category = category;
		this.value = value;
	}

	public C getCategory() {
		return category;
	}

	public V getValue() {
		return value;
	}

	@Override
	public int compareTo(ParetoValue<C, V> other) {
		return this.getValue().compareTo(other.getValue());
	}

}
