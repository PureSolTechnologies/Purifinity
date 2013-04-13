package com.puresol.coding.client.common.chart.renderer;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;

import com.puresol.coding.client.common.chart.DataPoint2D;

/**
 * This color provider is used for {@link PlotRenderer} to provide color
 * information for each mark. This can be used to apply gradient coloring for
 * histograms for instance.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ColorProvider {

	/**
	 * This method returns the foreground color which is needed by
	 * {@link MarkRenderer} to render the mark in an appropriate color if
	 * possible.
	 * 
	 * <b>Attention(!):</b> The caller of this method needs to do a
	 * {@link Color#dispose()} to free the resources.
	 * 
	 * @param dataPoint
	 *            is the data point to be rendered.
	 * @return A {@link Color} is returned. Null may be returned if the default
	 *         color is to be used.
	 */
	public Color provideForegroundColor(Device device, DataPoint2D dataPoint);

	/**
	 * This method returns the background color which is needed by
	 * {@link MarkRenderer} to render the mark in an appropriate color if
	 * possible.
	 * 
	 * <b>Attention(!):</b> The caller of this method needs to do a
	 * {@link Color#dispose()} to free the resources.
	 * 
	 * @param dataPoint
	 *            is the data point to be rendered.
	 * @return A {@link Color} is returned. Null may be returned if the default
	 *         color is to be used.
	 */
	public Color provideBackgroundColor(Device device, DataPoint2D dataPoint);

}
