package com.puresoltechnologies.purifinity.client.common.chart;

/**
 * This class represents a single 2D data point. A 2D data point always consists
 * of an x and y value. Additionally, a size and a remark can be applied, if
 * needed. The size may be used to calculate an alternative mark size or color
 * and the remark can be used to label or explain data points like outliers and
 * mavericks.
 * 
 * @author Rick-Rainer Ludwig
 */
public class GenericMark2D<TX, TY> implements Mark2D<TX, TY> {

	private final TX x;
	private final TY y;
	private final Double size;
	private final String remark;
	private final Object reference;

	public GenericMark2D(TX x, TY y) {
		this(x, y, null, null, null);
	}

	public GenericMark2D(TX x, TY y, Object reference) {
		this(x, y, null, null, reference);
	}

	public GenericMark2D(TX x, TY y, Double size) {
		this(x, y, size, null, null);
	}

	public GenericMark2D(TX x, TY y, Double size, Object reference) {
		this(x, y, size, null, reference);
	}

	public GenericMark2D(TX x, TY y, String remark) {
		this(x, y, null, remark, null);
	}

	public GenericMark2D(TX x, TY y, String remark, Object reference) {
		this(x, y, null, remark, reference);
	}

	public GenericMark2D(TX x, TY y, Double size, String remark) {
		this(x, y, size, remark, null);
	}

	public GenericMark2D(TX x, TY y, Double size, String remark,
			Object reference) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.remark = remark;
		this.reference = reference;
	}

	@Override
	public TX getX() {
		return x;
	}

	@Override
	public TY getY() {
		return y;
	}

	@Override
	public Double getSize() {
		return size;
	}

	@Override
	public String getRemark() {
		return remark;
	}

	@Override
	public Object getReference() {
		return reference;
	}

	@Override
	public String toString() {
		String string = String.format("%s;%s", x.toString(), y.toString());
		if (size != null) {
			string += " / " + size.toString();
		}
		if (remark != null) {
			string += " (" + remark + ")";
		}
		return string;
	}

}
