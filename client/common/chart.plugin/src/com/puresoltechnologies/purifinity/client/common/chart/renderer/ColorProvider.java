package com.puresoltechnologies.purifinity.client.common.chart.renderer;

import org.eclipse.swt.graphics.RGB;

/**
 * This color provider is used for {@link PlotRenderer} and other plots like
 * area plots to provide color information for each mark. This can be used to
 * apply gradient coloring for histograms for instance.
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
	 * @param value
	 *            is the value to be rendered.
	 * @return A {@link RGB} is returned. Null may be returned if the default
	 *         color is to be used.
	 */
	public RGB getForegroundColor(Object value);

	/**
	 * This method returns the background color which is needed by
	 * {@link MarkRenderer} to render the mark in an appropriate color if
	 * possible.
	 * 
	 * @param value
	 *            is the data point to be rendered.
	 * @return A {@link RGB} is returned. Null may be returned if the default
	 *         color is to be used.
	 */
	public RGB getBackgroundColor(Object value);

}
