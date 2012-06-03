package com.puresol.coding.client.charting;

import java.util.List;

/**
 * This interface represents a single axis. It is the data about the axis itself
 * which is added to a chart and where a dataset can be bound to. The interface
 * is not specific for a direction.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Axis {

    /**
     * Adds a new category.
     * 
     * @param category
     *            is the name of the category to be added.
     */
    public void addCategory(String category);

    /**
     * Returns whether this axis is of type category or not.
     * 
     * @return True is returned in case of a category axis. False otherwise.
     */
    public boolean isCategoryAxis();

    /**
     * Returns the list of categories.
     * 
     * @return
     */
    public List<String> getCategories();

    /**
     * This method returns the direction of the axis.
     * 
     * @return
     */
    public AxisDirection getDirection();

    /**
     * This method sets the maximum value for the axis.
     * 
     * @param minimum
     */
    public void setMinimum(double minimum);

    /**
     * This method returns the minimum for the axis.
     * 
     * @return
     */
    public double getMinimum();

    /**
     * This method sets the minimum value for the axis.
     * 
     * @param minimum
     */
    public void setMaximum(double maximum);

    /**
     * This method returns the maximum for the axis.
     * 
     * @return
     */
    public double getMaximum();

    /**
     * This method sets the number of main ticks to be used.
     * 
     * For linear scale the ticks are drawn for the minimum and maximum and the
     * remaining evenly in between.
     * 
     * For logarithmic scale the thicks are drawn for minimum and maximum, too.
     * The remaining are put in between evenly for the log10.
     * 
     * For the behavior, the minimum number of ticks is two!
     * 
     * @param number
     */
    public void setMainTicks(int number);

    /**
     * This method returns the number of main ticks.
     * 
     * @return
     */
    public int getMainTicks();

    /**
     * This method sets the number of sub ticks to be used. This is the number
     * to be drawn between the main ticks.
     * 
     * For linear scale the ticks are drawn evenly in between.
     * 
     * For logarithmic scale the thicks are drawn evenly for the log10.
     * 
     * @param number
     */
    public void setSubTicks(int number);

    /**
     * This method returns the number of sub ticks.
     * 
     * @return
     */
    public int getSubTicks();

    /**
     * This method sets whether the axis is logarithmic scaled or not. Two sided
     * is set to false by default.
     * 
     * @param logarithmic
     */
    public void setLogarithmic(boolean logarithmic);

    /**
     * This method sets whether the axis is logarithmic scaled or not. Two sided
     * is set to false by default.
     * 
     * @param logarithmic
     * @param twoSided
     *            means, that negative values are allowed, too. In this case the
     *            negative numbers are taken positive and calculated normally,
     *            but shown on the negative axis. Minimum is used to draw the
     *            zero gap which is mathematically impossible to draw.
     */
    public void setLogarithmic(boolean logarithmic, boolean twoSided);

    /**
     * This method returns whether the axis is in logarithmic mode or not.
     * 
     * @return
     */
    public boolean isLogarithmic();

    /**
     * This method returns whether the logarithmic mode is two sided or not.
     * 
     * @return
     */
    public boolean isTwoSidedLogarithmic();

}
