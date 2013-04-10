package com.puresol.coding.client.common.chart.old;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a base class for 2D charts.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CategoryChart extends AbstractChart2D {

	private final List<CategoryValuePair> categoryData = new ArrayList<CategoryValuePair>();
	private XAxis xAxis = new XAxis();
	private YAxis yAxis = new YAxis();

	public final void setData(List<CategoryValuePair> categoryData) {
		this.categoryData.clear();
		this.categoryData.addAll(categoryData);
	}

	public final void addData(CategoryValuePair categoryData) {
		this.categoryData.add(categoryData);
	}

	public final List<CategoryValuePair> getData() {
		return categoryData;
	}

	public final void setXAxis(XAxis axis) {
		xAxis = axis;
	}

	public final void setYAxis(YAxis axis) {
		yAxis = axis;
	}

	public final XAxis getXAxis() {
		return xAxis;
	}

	public final YAxis getYAxis() {
		return yAxis;
	}

}
