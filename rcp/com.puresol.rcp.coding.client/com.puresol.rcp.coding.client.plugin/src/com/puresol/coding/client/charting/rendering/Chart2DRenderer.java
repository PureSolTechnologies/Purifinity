package com.puresol.coding.client.charting.rendering;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

import com.puresol.coding.client.charting.AxisDirection;
import com.puresol.coding.client.charting.BorderPosition;
import com.puresol.coding.client.charting.Chart2D;
import com.puresol.coding.client.charting.Legend;
import com.puresol.coding.client.charting.Title;

/**
 * This is a simple {@link Chart2D} renderer.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Chart2DRenderer {

    private final BorderPositionCounter titleCount = new BorderPositionCounter();
    private final BorderPositionCounter legendCount = new BorderPositionCounter();
    private final Map<AxisDirection, Map<AxisPosition, Integer>> axisCount = new HashMap<AxisDirection, Map<AxisPosition, Integer>>();

    private GC gc;
    private Rectangle clientArea;
    private Display display;
    private Chart2D chart2D;

    public void render(GC gc, Rectangle clientArea, Display display,
	    Chart2D chart2D) {
	this.gc = gc;
	this.clientArea = clientArea;
	this.display = display;
	this.chart2D = chart2D;

	Rectangle titleCenterArea = drawTitles();
	Rectangle legendCenterArea = drawLegends(titleCenterArea);
	new Graph2DRenderer().render(gc, chart2D, legendCenterArea);
    }

    private Rectangle drawTitles() {
	// init fields and variables...
	titleCount.clear();
	Map<BorderPosition, Integer> nums = new HashMap<BorderPosition, Integer>();
	for (BorderPosition position : BorderPosition.values()) {
	    nums.put(position, 0);
	}
	// render
	List<Title> titles = chart2D.getTitles();
	for (Title title : titles) {
	    titleCount.add(title.getPosition());
	}
	BorderPositionCalculator positionCalculator = new BorderPositionCalculator(
		clientArea, titleCount, 0.05);
	Font font = new Font(gc.getDevice(), "Times",
		(int) (clientArea.height * 0.05 * 0.95), SWT.BOLD);
	try {
	    gc.setFont(font);
	    FontMetrics fontMetrics = gc.getFontMetrics();
	    int fontHeight = fontMetrics.getHeight();
	    int averageCharWidth = fontMetrics.getAverageCharWidth();
	    for (Title title : titles) {
		Integer num = nums.get(title.getPosition());
		Rectangle titleArea = positionCalculator.getPosition(
			title.getPosition(), num,
			titleCount.get(title.getPosition()));
		String titleText = title.getText();
		gc.drawText(titleText, titleArea.x + titleArea.width / 2
			- titleText.length() * averageCharWidth / 2,
			titleArea.y + titleArea.height / 2 - fontHeight / 2);
		nums.put(title.getPosition(), num + 1);
	    }
	    return positionCalculator.getCenterArea();
	} finally {
	    font.dispose();
	}
    }

    private Rectangle drawLegends(Rectangle titleCenterArea) {
	// init fields and variables...
	legendCount.clear();
	Map<BorderPosition, Integer> nums = new HashMap<BorderPosition, Integer>();
	for (BorderPosition position : BorderPosition.values()) {
	    nums.put(position, 0);
	}
	// render
	List<Legend> legends = chart2D.getLegends();
	for (Legend legend : legends) {
	    legendCount.add(legend.getPosition());
	}
	BorderPositionCalculator positionCalculator = new BorderPositionCalculator(
		titleCenterArea, legendCount, 0.2);
	for (Legend legend : legends) {
	    Integer num = nums.get(legend.getPosition());
	    Rectangle legendArea = positionCalculator.getPosition(
		    legend.getPosition(), num,
		    legendCount.get(legend.getPosition()));
	    RendererUtils.drawCrossedBox(gc, legendArea);
	    nums.put(legend.getPosition(), num + 1);
	}
	return positionCalculator.getCenterArea();
    }
}
