package com.puresol.coding.client.charting.rendering;

/**
 * This enum specifies the type of the axis to be rendered.
 * 
 * @author Rick-Rainer Ludwig
 */
public enum AxisType {

    /**
     * This axis type is a single direction pointer. It originates at the
     * coordinate system origin and points to the positive direction.
     */
    SINGLE,
    /**
     * This type draws a box for the axis (rectangle for 2D and a cuboid for 3D)
     */
    BOX;

}
