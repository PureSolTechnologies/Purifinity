/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresol.purifinity.utils.io;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import com.puresol.purifinity.utils.data.TypeWrapper;

/**
 * This class represents the complete information about the type of a variable.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ValueType implements Comparable<ValueType> {

	/**
	 * This variable keeps the class object stored for later use within the
	 * object.
	 */
	protected Class<?> classObject = null;

	/**
	 * This variable stores the information whether the variable is descrete or
	 * not.
	 */
	protected boolean descrete = false;

	/**
	 * This variable stores whether the type is numeric or not.
	 */
	protected boolean numeric = false;

	/**
	 * This variable stores whether the type is a string or not.
	 */
	protected boolean string = false;

	static public ValueType fromClassName(String className) {
		return new ValueType(className);
	}

	static public ValueType fromClass(Class<?> clazz) {
		return new ValueType(clazz);
	}

	static public ValueType fromObject(Object object) {
		return new ValueType(object.getClass());
	}

	/**
	 * This constructor sets all internal values depended to a Class the
	 * variable is type of.
	 * 
	 * @param c
	 *            is the Class of the variable.
	 */
	private ValueType(Class<?> c) {
		set(c);
	}

	/**
	 * This constructor sets all internal values depended to a Class the
	 * variable is type of.
	 * 
	 * @param c
	 *            is the Class of the variable.
	 */
	private ValueType(String className) {
		Class<?> c;
		try {
			c = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Class '" + className
					+ "' was not found.");
		}
		set(c);
	}

	/**
	 * This method sets the internal values in dependence to a Class.
	 * 
	 * @param c
	 *            is the Class the properties are taken out.
	 */
	public void set(Class<?> c) throws IllegalArgumentException {
		if (c == null) {
			throw new IllegalArgumentException("c is null!");
		}
		classObject = c;
		if (Integer.class.equals(c)) {
			descrete = true;
			numeric = true;
			string = false;
		} else if (Float.class.equals(c)) {
			descrete = false;
			numeric = true;
			string = false;
		} else if (Double.class.equals(c)) {
			descrete = false;
			numeric = true;
			string = false;
		} else if (String.class.equals(c)) {
			descrete = false;
			numeric = false;
			string = true;
		} else if (Boolean.class.equals(c)) {
			descrete = true;
			numeric = false;
			string = false;
		} else if (Date.class.equals(c)) {
			descrete = true;
			numeric = false;
			string = false;
		} else {
			descrete = false;
			numeric = false;
			string = false;
		}
	}

	/**
	 * This method returns the variable type's class name.
	 * 
	 * @return The name of the variable type's class is returned.
	 */
	public String getClassName() {
		return classObject.getName();
	}

	/**
	 * This method returns the variable class.
	 * 
	 * @return The Class of the variable class is returned.
	 */
	public Class<?> getClassObject() {
		return classObject;
	}

	/**
	 * This method returns the descrete property.
	 * 
	 * @return True is returned, if the type is descrete like an Integer.
	 *         Otherwise false is returned.
	 */
	public boolean isDescrete() {
		return descrete;
	}

	/**
	 * This method returns the numeric property.
	 * 
	 * @return True is returned, if the type is numeric like an Integer or
	 *         Double. Otherwise false is returned.
	 */
	public boolean isNumeric() {
		return numeric;
	}

	/**
	 * This method returns the string property.
	 * 
	 * @return True is returned, if the type is a string like a String.
	 *         Otherwise false is returned.
	 */
	public boolean isString() {
		return string;
	}

	/**
	 * This method checks whether another class is equal to this variable type
	 * or not.
	 * 
	 * @param c
	 *            is a class which to check against.
	 * @return true is returned if the stored variable type is equal to c.
	 *         Otherwise false is returned.
	 */
	public boolean is(Class<?> clazz) {
		if (clazz == null) {
			return false;
		}
		return getClassObject().equals(clazz);
	}

	/**
	 * This method checks whether another class is instance of this variable
	 * type or not.
	 * 
	 * @param c
	 *            is a class which to check against.
	 * @return true is returned if the stored variable type is cast able to c.
	 *         Otherwise false is returned.
	 */
	public boolean isAssignableFrom(Class<?> c) {
		return this.classObject.isAssignableFrom(c);
	}

	public String toString() {
		return getClassName();
	}

	public static ValueType recognizeFromString(String value) {
		for (Class<?> wrapper : TypeWrapper.PRIMITIVE_WRAPPERS) {
			if (wrapper.equals(Character.class)) {
				// Character does not have a valueOf method!
				if (value.length() == 1) {
					return new ValueType(wrapper);
				}
				continue;
			}
			try {
				Method method = wrapper.getMethod("valueOf", String.class);
				Object o = method.invoke(wrapper, value);
				if (wrapper.equals(Float.class)) {
					Float f = (Float) o;
					if (f.isInfinite()) {
						continue;
					}
					if (f.isNaN()) {
						continue;
					}
				} else if (wrapper.equals(Double.class)) {
					Double d = (Double) o;
					if (d.isInfinite()) {
						continue;
					}
					if (d.isNaN()) {
						continue;
					}
				} else if (wrapper.equals(Boolean.class)) {
					if (!(value.equalsIgnoreCase("true")
							|| value.equalsIgnoreCase("false")
							|| value.equalsIgnoreCase("on") || value
							.equalsIgnoreCase("off"))) {
						continue;
					}
				}
				return new ValueType(wrapper);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// nothing to catch here, it's expected due to test...
			}
		}
		return new ValueType(String.class);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((classObject == null) ? 0 : classObject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValueType other = (ValueType) obj;
		if (classObject == null) {
			if (other.classObject != null)
				return false;
		} else if (!classObject.equals(other.classObject))
			return false;
		return true;
	}

	private int getIndex(Class<?> type) {
		for (int index = 0; index < TypeWrapper.PRIMITIVE_WRAPPERS.length; index++) {
			if (TypeWrapper.PRIMITIVE_WRAPPERS[index].equals(type)) {
				return index;
			}
			if (TypeWrapper.PRIMITIVES[index].equals(type)) {
				return index;
			}
		}
		return TypeWrapper.PRIMITIVE_WRAPPERS.length;
	}

	@Override
	public int compareTo(ValueType other) {
		if (other == null) {
			return 1;
		}
		if (equals(other)) {
			return 0;
		}
		int otherIndex = getIndex(other.getClassObject());
		int thisIndex = getIndex(this.getClassObject());
		if (thisIndex > otherIndex) {
			return 1;
		} else if (thisIndex < otherIndex) {
			return -1;
		}
		return 0;
	}
}
