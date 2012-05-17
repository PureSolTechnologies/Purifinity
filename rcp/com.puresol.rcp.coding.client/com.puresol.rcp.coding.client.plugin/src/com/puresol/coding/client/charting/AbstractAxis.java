package com.puresol.coding.client.charting;

/**
 * This is an abstract axis implementation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractAxis implements Axis {

    private double minimum = 0.0;
    private double maximum = 0.0;
    private int numMainTicks = 2;
    private int numSubTicks = 9;
    private boolean logarithmic = false;
    private boolean twoSided = false;

    @Override
    public final void setMinimum(double minimum) {
	this.minimum = minimum;
    }

    @Override
    public final double getMinimum() {
	return minimum;
    }

    @Override
    public final void setMaximum(double maximum) {
	this.maximum = maximum;
    }

    @Override
    public final double getMaximum() {
	return maximum;
    }

    @Override
    public final void setMainTicks(int number) {
	this.numMainTicks = number;
    }

    @Override
    public final int getMainTicks() {
	return numMainTicks;
    }

    @Override
    public final void setSubTicks(int number) {
	this.numSubTicks = number;
    }

    @Override
    public final int getSubTicks() {
	return numSubTicks;
    }

    @Override
    public final void setLogarithmic(boolean logarithmic) {
	this.logarithmic = logarithmic;
    }

    @Override
    public final void setLogarithmic(boolean logarithmic, boolean twoSided) {
	this.logarithmic = logarithmic;
	this.twoSided = twoSided;
    }

    @Override
    public final boolean isLogarithmic() {
	return logarithmic;
    }

    @Override
    public final boolean isTwoSidedLogarithmic() {
	return twoSided;
    }

}
