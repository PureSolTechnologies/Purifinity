package com.puresol.combatcoding;

/**
 * CC stands for Combat Coding and is collection of classes which implement a
 * standard functionality for a couple of classes which are implemented in a
 * standard way by reflection tools. This shall help to develop faster and more
 * secure classes by well tested methods and routines.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CC {

	/**
	 * This method implements a convinient method for equality check which can
	 * be used as a fast implementation in any other class with
	 * "return equals(this, other);".
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public static boolean equals(Object left, Object right) {
		return new EqualityChecker(left, right).isEqual();
	}

	/**
	 * This method calculates a hashCode for an object. This can be used for
	 * combat coding the hashCode method with "return CC.hashCode(this);".
	 * 
	 * @param source
	 * @return
	 */
	public static int hashCode(Object source) {
		return new HashCodeGenerator(source).getHashCode();
	}

	/**
	 * This method is meant to be used for combat coding of a clone function as
	 * "return CC.clone(this);".
	 * 
	 * @param <T>
	 * @param source
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public static <T> T clone(T source) throws CloneNotSupportedException {
		return new Cloner<T>(source).getClone();
	}
}
