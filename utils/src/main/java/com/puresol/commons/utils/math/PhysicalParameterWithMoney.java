package com.puresol.commons.utils.math;

/**
 * This class is for describing physical values. This can be used to store
 * additional information about a certain date.
 * 
 * Several information are added beside the name like a unit, the level of
 * measurement and a description.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PhysicalParameterWithMoney<T> extends
		ParameterWithArbitraryUnit<T> {

	private static final long serialVersionUID = -3381374301031154841L;

	private final CompoundSIUnitWithMoney unit;

	public PhysicalParameterWithMoney(String name,
			CompoundSIUnitWithMoney unit,
			LevelOfMeasurement levelOfMeasurement, String description,
			Class<T> type) {
		super(name, unit.toString(), levelOfMeasurement, description, type);
		this.unit = unit;
	}

	public final CompoundSIUnitWithMoney getPhysicalUnit() {
		return unit;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer(getName());
		if (unit != null) {
			String unitString = getUnit();
			if ((unitString != null) && (!unitString.isEmpty())) {
				buffer.append(" [").append(unitString).append("]");
			}
		}
		String description = getDescription();
		if ((description != null) && (!description.isEmpty())) {
			buffer.append(" (").append(description).append(")");
		}
		LevelOfMeasurement levelOfMeasurement = getLevelOfMeasurement();
		if (levelOfMeasurement != null) {
			buffer.append(" {").append(levelOfMeasurement).append("}");
		}
		return buffer.toString();
	}
}
