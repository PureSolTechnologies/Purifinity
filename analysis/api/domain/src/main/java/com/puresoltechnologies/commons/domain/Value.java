package com.puresoltechnologies.commons.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * <p>
 * This interface represents a value. A value always consists of two parts:
 * </p>
 * <ol>
 * <li>A value which represents a measurement or state.</li>
 * <li>A parameter of type {@link Parameter} which specifies which kind of value
 * it is.</li>
 * </ol>
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            is the type of the value to be stored.
 */
@JsonTypeInfo(use = Id.CLASS, include = As.PROPERTY, property = "class")
public interface Value<T> extends Serializable {

    /**
     * This method returns the stored value.
     * 
     * @return An object of type T is returned.
     */
    public T getValue();

    /**
     * This method returns the corresponding parameter.
     * 
     * @return An object of type {@link Parameter} is returned.
     */
    public Parameter<T> getParameter();
}
