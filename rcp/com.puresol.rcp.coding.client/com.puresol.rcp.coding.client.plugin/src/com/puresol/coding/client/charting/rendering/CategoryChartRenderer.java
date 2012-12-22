package com.puresol.coding.client.charting.rendering;

import java.util.List;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;

import com.puresol.coding.client.charting.BorderPosition;
import com.puresol.coding.client.charting.CategoryChart;
import com.puresol.coding.client.charting.CategoryValuePair;
import com.puresol.coding.client.charting.XAxis;
import com.puresol.coding.client.charting.YAxis;

public class CategoryChartRenderer {

    private final AxisRenderer renderer = new AxisRenderer();

    private GC gc;
    private CategoryChart chart;
    private TransformationMatrix2D transformation;

    public void render(GC gc, CategoryChart chart,
	    TransformationMatrix2D transformation) {
	this.gc = gc;
	this.chart = chart;
	this.transformation = transformation;
	drawSingleAxes();
	XAxis xAxis = chart.getXAxis();
	List<String> categories = xAxis.getCategories();
	List<CategoryValuePair> data = chart.getData();
	for (int i = 0; i < categories.size(); i++) {
	    String categoryName = categories.get(i);
	    for (CategoryValuePair valuePair : data) {
		if (categoryName.equals(valuePair.getCategoryName())) {
		    double value = valuePair.getValue();
		    Point2D point1 = new Point2D(i + 0.1, 0);
		    Point2D point2 = new Point2D(i + 1.0 - 0.1, value);
		    point1 = transformation.transform(point1);
		    point2 = transformation.transform(point2);
		    gc.setForeground(new Color(gc.getDevice(), new RGB(255, 0,
			    0)));
		    gc.setBackground(new Color(gc.getDevice(), new RGB(255, 0,
			    0)));
		    gc.fillRectangle((int) point1.getX(), (int) point1.getY(),
			    (int) (point2.getX() - point1.getX()),
			    (int) (point2.getY() - point1.getY()));
		}
	    }
	}
    }

    private void drawSingleAxes() {
	XAxis xAxis = chart.getXAxis();
	YAxis yAxis = chart.getYAxis();
	BorderPositionCounter counter = new BorderPositionCounter();
	counter.add(BorderPosition.SOUTH);
	counter.add(BorderPosition.WEST);
	renderer.render(gc, xAxis, transformation);
	renderer.render(gc, yAxis, transformation);
    }
}