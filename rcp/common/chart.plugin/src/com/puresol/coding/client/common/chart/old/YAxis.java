package com.puresol.coding.client.common.chart.old;

import com.puresol.coding.client.common.chart.AxisDirection;


/**
 * This is the class for an YAxis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class YAxis extends AbstractAxis {

	public YAxis() {
		super();
	}

	public YAxis(double min, double max, int mainTicks, int subTicks) {
		super(min, max, mainTicks, subTicks);
	}

	public YAxis(double min, double max, int mainTicks) {
		super(min, max, mainTicks);
	}

	public YAxis(double min, double max) {
		super(min, max);
	}

	@Override
	public AxisDirection getDirection() {
		return AxisDirection.Y;
	}

}
