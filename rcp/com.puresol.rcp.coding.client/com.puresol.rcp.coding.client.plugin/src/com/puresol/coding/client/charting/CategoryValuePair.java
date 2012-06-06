package com.puresol.coding.client.charting;

public class CategoryValuePair {

    private final String categoryName;
    private final double value;

    public CategoryValuePair(String categoryName, double value) {
	super();
	this.categoryName = categoryName;
	this.value = value;
    }

    public String getCategoryName() {
	return categoryName;
    }

    public double getValue() {
	return value;
    }

}
