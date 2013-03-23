package com.puresol.utils.math;

import java.io.Serializable;

public interface Parameter<T> extends Serializable {

    public String getName();

    public String getUnit();

    public String getDescription();

    public LevelOfMeasurement getLevelOfMeasurement();

    public Class<T> getType();

}
