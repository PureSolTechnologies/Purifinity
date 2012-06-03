package com.puresol.coding.client.charting;

/**
 * This is the class for an XAxis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class XAxis extends AbstractAxis {

    public XAxis() {
	super();
    }

    public XAxis(double min, double max, int mainTicks, int subTicks) {
	super(min, max, mainTicks, subTicks);
    }

    public XAxis(double min, double max, int mainTicks) {
	super(min, max, mainTicks);
    }

    public XAxis(double min, double max) {
	super(min, max);
    }

    @Override
    public AxisDirection getDirection() {
	return AxisDirection.X;
    }
}
