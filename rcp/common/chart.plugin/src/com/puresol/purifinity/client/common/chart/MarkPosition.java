package com.puresol.purifinity.client.common.chart;

import org.eclipse.swt.graphics.Rectangle;

public class MarkPosition {

	private final Plot<?, ?> plot;
	private final int dataPointIndex;
	private final Rectangle position;

	public MarkPosition(Plot<?, ?> plot, int dataPointIndex, Rectangle position) {
		super();
		this.plot = plot;
		this.dataPointIndex = dataPointIndex;
		this.position = position;
	}

	public Plot<?, ?> getPlot() {
		return plot;
	}

	public int getDataPointIndex() {
		return dataPointIndex;
	}

	public Rectangle getPosition() {
		return position;
	}

}
