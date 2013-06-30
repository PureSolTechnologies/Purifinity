package com.puresol.purifinity.utils.math;

public class ParameterWithArbitraryUnit<T> extends AbstractParameter<T> {

    private static final long serialVersionUID = -8201047425620279245L;

    private final String unit;

    public ParameterWithArbitraryUnit(String name, String unit,
	    LevelOfMeasurement levelOfMeasurement, String description,
	    Class<T> type) {
	super(name, levelOfMeasurement, description, type);
	this.unit = unit;
    }

    @Override
    public String getUnit() {
	return unit;
    }
}
