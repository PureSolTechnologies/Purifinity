package com.puresol.coding.client.common.chart;

import java.util.ArrayList;
import java.util.List;

public class Chart2D {

	private String title;
	private String subTitle;

	private Axis<?> xAxis;
	private Axis<?> yAxis;
	private Axis<?> alternateXAxis;
	private Axis<?> alternateYAxis;
	private final List<Plot<?, ?>> plots = new ArrayList<Plot<?, ?>>();

	public Chart2D() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Axis<?> getXAxis() {
		return xAxis;
	}

	public void setxAxis(Axis<?> xAxis) {
		this.xAxis = xAxis;
	}

	public Axis<?> getYAxis() {
		return yAxis;
	}

	public void setyAxis(Axis<?> yAxis) {
		this.yAxis = yAxis;
	}

	public Axis<?> getAlternateXAxis() {
		return alternateXAxis;
	}

	public void setAlternateXAxis(Axis<?> alternateXAxis) {
		this.alternateXAxis = alternateXAxis;
	}

	public Axis<?> getAlternateYAxis() {
		return alternateYAxis;
	}

	public void setAlternateYAxis(Axis<?> alternateYAxis) {
		this.alternateYAxis = alternateYAxis;
	}

	public List<Plot<?, ?>> getPlots() {
		return plots;
	}

	public void addPlot(Plot<?, ?> plot) {
		if ((!plot.getXAxis().equals(xAxis))
				&& (!plot.getXAxis().equals(alternateXAxis))) {
			throw new IllegalArgumentException(
					"X axis of plot does not match set x axes.");
		}
		if ((!plot.getYAxis().equals(yAxis))
				&& (!plot.getYAxis().equals(alternateYAxis))) {
			throw new IllegalArgumentException(
					"Y axis of plot does not match set y axes.");
		}
		plots.add(plot);
	}

	public void removeAllPlots() {
		plots.clear();
	}

}
