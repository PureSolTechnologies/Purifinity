package com.puresoltechnologies.purifinity.client.common.chart.renderer;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import com.puresoltechnologies.purifinity.client.common.chart.Plot;
import com.puresoltechnologies.purifinity.client.common.chart.math.TransformationMatrix2D;

/**
 * This is the central interface for all chart mark renderer. Marks are the
 * symbols which are displayed for each data point. For bar charts it may be
 * also a bar.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface MarkRenderer {

	/**
	 * This method will paint the actual mark.
	 * 
	 * <b>Attention(!):</b> The colors must no be reset! The foreground and
	 * background color is set from the {@link PlotRenderer} with its
	 * {@link ColorProvider}.
	 * 
	 * @param transformation
	 *            is the transformation matrix to be used to calculate the
	 *            positions.
	 * @param plot
	 *            is the {@link Plot} to be rendered. Some information of this
	 *            object is used for rendering.
	 * @param x
	 *            is the x value to be rendered.
	 * @param y
	 *            is the y value to be rendered.
	 */
	public Rectangle render(GC gc, TransformationMatrix2D transformation,
			Plot<?, ?> plot, Object x, Object y);

}
