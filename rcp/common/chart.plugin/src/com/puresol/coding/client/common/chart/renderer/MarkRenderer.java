package com.puresol.coding.client.common.chart.renderer;

import org.eclipse.swt.graphics.GC;

import com.puresol.coding.client.common.chart.DataPoint2D;
import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;

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
	 * @param point
	 *            is the data point to be drawn.
	 */
	public void render(GC gc, TransformationMatrix2D transformation,
			DataPoint2D point);

}
