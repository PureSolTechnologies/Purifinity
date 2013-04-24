package com.puresol.coding.client.common.chart;

import org.eclipse.swt.graphics.RGB;

public class VerticalColoredArea<TX, TY> extends ColoredArea<TX, TY> {

	public VerticalColoredArea(Plot<TX, TY> plot, double minX, double maxX,
			RGB color) {
		super(plot, minX, maxX, plot.getYAxis().getMinimum(), plot.getYAxis()
				.getMaximum(), color);
	}

}
