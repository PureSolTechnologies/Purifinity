package com.puresoltechnologies.purifinity.client.common.chart.math;

import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D;

/**
 * This is a special {@link Space} implementation for 2D transformation space.
 * The 2D transformation space has three dimensions to also enable the
 * translation transformation.
 * 
 * @author Rick-Rainer Ludwig
 */
public class TransformationSpace2D implements Space {

	private static final long serialVersionUID = -8876216868810695839L;

	private static final TransformationSpace2D INSTANCE = new TransformationSpace2D();

	/**
	 * Returns the final static instance of this transformation space.s
	 * 
	 * @return A {@link TransformationSpace2D} object is returned.
	 */
	public static TransformationSpace2D getInstance() {
		return INSTANCE;
	}

	/**
	 * Private constructor to avoid direct instantiation.
	 */
	private TransformationSpace2D() {
	}

	@Override
	public int getDimension() {
		return 4;
	}

	@Override
	public Space getSubSpace() throws MathUnsupportedOperationException {
		return Euclidean3D.getInstance();
	}

}
