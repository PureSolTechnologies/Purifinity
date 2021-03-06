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

package com.puresoltechnologies.purifinity.server.common.utils.data;

import java.util.Hashtable;

/**
 * This class converts primitive types to wrapper classes and vice versa.
 * 
 * The classes used are: byte &lt;--&gt; Byte, short &lt;--&gt; Short, int
 * &lt;--&gt; Integer, long &lt;--&gt; Long, float &lt;--&gt; Float, double
 * &lt;--&gt; Double, char &lt;--&gt; Character, and boolean &lt;--&gt; Boolean.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TypeWrapper {

    // constants...
    public static final Class<?> PRIMITIVE_WRAPPERS[] = { Byte.class, Short.class, Integer.class, Long.class,
	    Float.class, Double.class, Character.class, Boolean.class };

    public static final Class<?> PRIMITIVES[] = { byte.class, short.class, int.class, long.class, float.class,
	    double.class, char.class, boolean.class };

    private static Hashtable<Class<?>, Class<?>> prim2wrap;
    private static Hashtable<Class<?>, Class<?>> wrap2prim;

    // initialize the static assignment hashes for fast conversion...
    static {
	if (PRIMITIVE_WRAPPERS.length != PRIMITIVES.length)
	    throw new RuntimeException("The number of primitives and wrappers have to be equal!");
	prim2wrap = new Hashtable<Class<?>, Class<?>>();
	wrap2prim = new Hashtable<Class<?>, Class<?>>();
	for (int i = 0; i < PRIMITIVE_WRAPPERS.length; i++) {
	    prim2wrap.put(PRIMITIVES[i], PRIMITIVE_WRAPPERS[i]);
	    wrap2prim.put(PRIMITIVE_WRAPPERS[i], PRIMITIVES[i]);
	}
    }

    /**
     * Checks whether a class is a primitive or a wrapper class.
     * 
     * @param clazz
     *            is the class to be checked.
     * @return <code>true</code> is returned in case of a type of primitive type
     *         or its wrapper.
     */
    public static boolean isPrimitiveOrWrapper(Class<?> clazz) {
	return (clazz.isPrimitive() || isPrimitiveWrapper(clazz));
    }

    /**
     * Checks for primitive wrapper.
     * 
     * @param clazz
     *            is the class to be checked.
     * @return <code>true</code> is returned if the class is a wrapper of a
     *         primitive type. <code>false</code> is returned otherwise.
     */
    public static boolean isPrimitiveWrapper(Class<?> clazz) {
	return wrap2prim.containsKey(clazz);
    }

    public static Class<?> toPrimitive(Class<?> clazz) {
	if (clazz.isPrimitive()) {
	    return clazz;
	}
	return wrap2prim.get(clazz);
    }

    public static Class<?> toPrimitiveWrapper(Class<?> clazz) {
	if (isPrimitiveWrapper(clazz)) {
	    return clazz;
	}
	return prim2wrap.get(clazz);
    }

    public static <T> T convertFromString(Class<T> type, String value) {
	if (Byte.class.equals(type)) {
	    Byte byteValue = Byte.parseByte(value);
	    @SuppressWarnings("unchecked")
	    T result = (T) byteValue;
	    return result;
	} else if (Short.class.equals(type)) {
	    Short shortValue = Short.parseShort(value);
	    @SuppressWarnings("unchecked")
	    T result = (T) shortValue;
	    return result;
	} else if (Integer.class.equals(type)) {
	    Integer integerValue = Integer.parseInt(value);
	    @SuppressWarnings("unchecked")
	    T result = (T) integerValue;
	    return result;
	} else if (Long.class.equals(type)) {
	    Long longValue = Long.parseLong(value);
	    @SuppressWarnings("unchecked")
	    T result = (T) longValue;
	    return result;
	} else if (Float.class.equals(type)) {
	    Float floatValue = Float.parseFloat(value);
	    @SuppressWarnings("unchecked")
	    T result = (T) floatValue;
	    return result;
	} else if (Double.class.equals(type)) {
	    Double doubleValue = Double.parseDouble(value);
	    @SuppressWarnings("unchecked")
	    T result = (T) doubleValue;
	    return result;
	} else if (Character.class.equals(type)) {
	    Character characterValue = value.charAt(0);
	    @SuppressWarnings("unchecked")
	    T result = (T) characterValue;
	    return result;
	} else if (String.class.equals(type)) {
	    @SuppressWarnings("unchecked")
	    T result = (T) value;
	    return result;
	} else if (Boolean.class.equals(type)) {
	    Boolean booleanValue = Boolean.parseBoolean(value);
	    @SuppressWarnings("unchecked")
	    T result = (T) booleanValue;
	    return result;
	}
	throw new IllegalArgumentException("Type '" + type + "' is not supported.");
    }
}
