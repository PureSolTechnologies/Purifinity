package com.puresol.coding.client.common.chart;

/**
 * This class represents a single 2D data point. A 2D data point always consists
 * of an x and y value. Additionally, a size and a remark can be applied, if
 * needed. The size may be used to calculate an alternative mark size or color
 * and the remark can be used to label or explain data points like outliers and
 * mavericks.
 * 
 * @author Rick-Rainer Ludwig
 */
public class DataPoint2D<TX, TY> {

	private final TX x;
	private final TY y;
	private final Double size;
	private final String remark;

	public DataPoint2D(TX x, TY y) {
		this(x, y, null, null);
	}

	public DataPoint2D(TX x, TY y, Double size) {
		this(x, y, size, null);
	}

	public DataPoint2D(TX x, TY y, String remark) {
		this(x, y, null, remark);
	}

	public DataPoint2D(TX x, TY y, Double size, String remark) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.remark = remark;
	}

	public TX getX() {
		return x;
	}

	public TY getY() {
		return y;
	}

	public Double getSize() {
		return size;
	}

	public String getRemark() {
		return remark;
	}

}
