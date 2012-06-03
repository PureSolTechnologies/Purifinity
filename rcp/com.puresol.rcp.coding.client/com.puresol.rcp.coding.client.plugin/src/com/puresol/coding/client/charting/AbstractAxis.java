package com.puresol.coding.client.charting;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an abstract axis implementation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractAxis implements Axis {

    private final List<String> categories = new ArrayList<String>();

    private double minimum = -1.0;
    private double maximum = 1.0;
    private int numMainTicks = 3;
    private int numSubTicks = 9;
    private boolean logarithmic = false;
    private boolean twoSided = false;

    public AbstractAxis() {
	super();
    }

    public AbstractAxis(double min, double max) {
	super();
	setMinimum(min);
	setMaximum(max);
    }

    public AbstractAxis(double min, double max, int mainTicks) {
	this(min, max);
	setMainTicks(mainTicks);
    }

    public AbstractAxis(double min, double max, int mainTicks, int subTicks) {
	this(min, max, mainTicks);
	setSubTicks(subTicks);
    }

    @Override
    public void addCategory(String category) {
	categories.add(category);
	setMinimum(0.0);
	setMaximum(categories.size());
	setMainTicks(categories.size() + 1);
	setSubTicks(0);
    }

    @Override
    public boolean isCategoryAxis() {
	return categories.size() > 0;
    }

    @Override
    public List<String> getCategories() {
	return categories;
    }

    @Override
    public void setMinimum(double minimum) {
	this.minimum = minimum;
    }

    @Override
    public double getMinimum() {
	return minimum;
    }

    @Override
    public void setMaximum(double maximum) {
	this.maximum = maximum;
    }

    @Override
    public double getMaximum() {
	return maximum;
    }

    @Override
    public void setMainTicks(int number) {
	this.numMainTicks = number;
    }

    @Override
    public int getMainTicks() {
	return numMainTicks;
    }

    @Override
    public void setSubTicks(int number) {
	this.numSubTicks = number;
    }

    @Override
    public int getSubTicks() {
	return numSubTicks;
    }

    @Override
    public void setLogarithmic(boolean logarithmic) {
	this.logarithmic = logarithmic;
    }

    @Override
    public void setLogarithmic(boolean logarithmic, boolean twoSided) {
	this.logarithmic = logarithmic;
	this.twoSided = twoSided;
    }

    @Override
    public boolean isLogarithmic() {
	return logarithmic;
    }

    @Override
    public boolean isTwoSidedLogarithmic() {
	return twoSided;
    }

}
