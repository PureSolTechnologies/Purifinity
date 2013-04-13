package com.puresol.coding.client.common.chart.renderer;

import com.puresol.coding.client.common.chart.math.Point2D;
import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;

/**
 * This is the central interface for all chart mark renderer. Marks are the
 * symbols which are displayed for each data point. For bar charts it may be
 * also a bar.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface MarkRenderer {

	public void render(TransformationMatrix2D transformation, Point2D point);

}
