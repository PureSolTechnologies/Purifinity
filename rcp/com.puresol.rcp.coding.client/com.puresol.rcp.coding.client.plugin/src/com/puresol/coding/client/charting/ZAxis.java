package com.puresol.coding.client.charting;

/**
 * This is the class for an ZAxis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ZAxis extends AbstractAxis {

    public ZAxis() {
	super();
    }

    public ZAxis(double min, double max, int mainTicks, int subTicks) {
	super(min, max, mainTicks, subTicks);
    }

    public ZAxis(double min, double max, int mainTicks) {
	super(min, max, mainTicks);
    }

    public ZAxis(double min, double max) {
	super(min, max);
    }

    @Override
    public AxisDirection getDirection() {
	return AxisDirection.Z;
    }

}
