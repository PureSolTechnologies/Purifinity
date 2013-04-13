package com.puresol.coding.client.common.chart;

import com.puresol.coding.client.common.chart.math.Point2D;

/**
 * This class represents a single 2D data point. A 2D data point always consists
 * of an x and y value. Additionally, a size and a remark can be applied, if
 * needed. The size may be used to calculate an alternative mark size or color
 * and the remark can be used to label or explain data points like outliers and
 * mavericks.
 * 
 * @author Rick-Rainer Ludwig
 */
public class DataPoint2D {

	private final Point2D point;
	private final Double size;
	private final String remark;

	public DataPoint2D(Point2D point) {
		this(point, null, null);
	}

	public DataPoint2D(Point2D point, Double size) {
		this(point, size, null);
	}

	public DataPoint2D(Point2D point, String remark) {
		this(point, null, remark);
	}

	public DataPoint2D(Point2D point, Double size, String remark) {
		super();
		this.point = point;
		this.size = size;
		this.remark = remark;
	}

	public Point2D getPoint() {
		return point;
	}

	public Double getSize() {
		return size;
	}

	public String getRemark() {
		return remark;
	}

}
