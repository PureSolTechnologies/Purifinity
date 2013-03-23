package com.puresol.utils.math;

public class ParameterWithArbitraryUnit extends AbstractParameter {

	private static final long serialVersionUID = -8201047425620279245L;

	private final String unit;

	public ParameterWithArbitraryUnit(String name, String unit,
			LevelOfMeasurement levelOfMeasurement, String description) {
		super(name, levelOfMeasurement, description);
		this.unit = unit;
	}

	@Override
	public String getUnit() {
		return unit;
	}

}
