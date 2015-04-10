package com.puresoltechnologies.commons.domain.money;

import com.puresoltechnologies.commons.domain.CompoundSIUnitWithMoney;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.domain.ParameterWithArbitraryUnit;

/**
 * This class is for describing physical values. Additionally, this object takes
 * a money dimension.
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
