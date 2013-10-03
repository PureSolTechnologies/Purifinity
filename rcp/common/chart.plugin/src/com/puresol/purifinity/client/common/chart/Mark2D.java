package com.puresol.purifinity.client.common.chart;

import com.puresol.purifinity.client.common.chart.renderer.MarkRenderer;

public interface Mark2D<TX, TY> {

	/**
	 * This method returns the X position of this mark.
	 * 
	 * @return An object of type TX is returned.
	 */
	public TX getX();

	/**
	 * This method returns the Y position of this mark.
	 * 
	 * @return An object of type TY is returned.
	 */
	public TY getY();

	/**
	 * <p>
	 * This method returns the size of the mark. This parameter is not always
	 * supported and depends on the {@link MarkRenderer} used.
	 * </p>
	 * <p>
	 * <b>The default should be to return null to signal the renderer to use the
	 * default size. Values of 0.0 or smaller values must not be returned.</b>
	 * </p>
	 * 
	 * @return The size is returned as {@link Double} value.
	 */
	public Double getSize();

	/**
	 * This method returns a remark for the mark. This remark is used for
	 * tooltips to show some information about the data point drawn.
	 * 
	 * @return A {@link String} is returned containing the remark.
	 */
	public String getRemark();

	/**
	 * This method returns a reference to the data object behind the data point.
	 * This reference is used for actions like clicks to give listeners the
	 * reference for further actions.
	 * 
	 * @return An {@link Object} is returned containing the reference. A cast is
	 *         needed by the listener to get access to the specific
	 *         functionality.
	 */
	public Object getReference();
}
