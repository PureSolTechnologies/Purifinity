package com.puresoltechnologies.commons.math;

import java.io.Serializable;

/**
 * This {@link Parameter} class is a representation of a single date value type.
 * This class does not provide a value (like a measurement) itself, but contains
 * all meta information about it.
 * 
 * For handling values an object which extends {@link Value} is used. Have a
 * look there for more information.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            is the actual value type class of the value itself. This class
 *            keeps the class to provide the information.
 */
public interface Parameter<T> extends Serializable {

	/**
	 * This method returns the name of the parameter.
	 * 
	 * @return A {@link String} containing the name is returned.
	 */
	public String getName();

	/**
	 * This method returns the {@link String} representation of the unit of the
	 * parameter.
	 * 
	 * @return A {@link String} is returned containing the unit.
	 */
	public String getUnit();

	/**
	 * This method returns a human readable short description about the
	 * parameter's meaning and content.
	 * 
	 * @return A {@link String} is returned containing the information about the
	 *         parameter.
	 */
	public String getDescription();

	/**
	 * This method returns the level of measurement for the parameter. Have a
	 * look to the {@link LevelOfMeasurement} enum for information about the
	 * different levels.
	 * 
	 * @return A {@link LevelOfMeasurement} enum value is returned.
	 */
	public LevelOfMeasurement getLevelOfMeasurement();

	/**
	 * This method returns the type {@link Class} for values of that parameter.
	 * 
	 * @return A {@link Class} is returned representing the actual values type.
	 */
	public Class<T> getType();

	/**
	 * This method returns whether the value of that parameter can be casted or
	 * converted to a number of not.
	 * 
	 * @return True is returned if the value is of numeric type. False is
	 *         returned otherwise.
	 */
	public boolean isNumeric();

}
