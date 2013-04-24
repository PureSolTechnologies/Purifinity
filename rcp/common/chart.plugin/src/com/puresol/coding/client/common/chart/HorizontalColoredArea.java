package com.puresol.coding.client.common.chart;

import org.eclipse.swt.graphics.RGB;

public class HorizontalColoredArea<TX, TY> extends ColoredArea<TX, TY> {

	public HorizontalColoredArea(Plot<TX, TY> plot, double minY, double maxY,
			RGB color) {
		super(plot, plot.getXAxis().getMinimum(), plot.getXAxis().getMaximum(),
				minY, maxY, color);
	}

}
