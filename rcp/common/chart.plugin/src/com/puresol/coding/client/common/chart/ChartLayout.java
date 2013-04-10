package com.puresol.coding.client.common.chart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

public class ChartLayout extends Layout {

	@Override
	protected Point computeSize(Composite composite, int wHint, int hHint,
			boolean flushCache) {
		return new Point(wHint, hHint);
	}

	@Override
	protected void layout(Composite composite, boolean flushCache) {
		Rectangle clientArea = composite.getClientArea();
		Control[] children = composite.getChildren();
		Point titleSize = new Point(0, 0);
		Point subTitleSize = new Point(0, 0);
		boolean hasPlot = false;
		boolean hasLegende = false;
		for (Control child : children) {
			Object layoutData = child.getLayoutData();
			if (layoutData instanceof ChartElement) {
				ChartElement element = (ChartElement) layoutData;
				switch (element) {
				case TITLE:
					titleSize = child.computeSize(SWT.DEFAULT, SWT.DEFAULT,
							true);
					break;
				case SUBTITLE:
					subTitleSize = child.computeSize(SWT.DEFAULT, SWT.DEFAULT,
							true);
					break;
				case PLOT:
					hasPlot = true;
					break;
				case LEGENDE:
					hasLegende = true;
					break;
				default:
				}
			}
		}
		int legendeWidth = 0;
		int plotWidth = 0;
		if (hasPlot) {
			if (hasLegende) {
				plotWidth = 2 * clientArea.width / 3;
				legendeWidth = clientArea.width / 3;
			} else {
				plotWidth = clientArea.width;
			}
		} else if (hasLegende) {
			legendeWidth = clientArea.width;
		}
		int titleAreaHeight = titleSize.y + subTitleSize.y;
		for (Control child : children) {
			Object layoutData = child.getLayoutData();
			if (layoutData instanceof ChartElement) {
				ChartElement element = (ChartElement) layoutData;
				Rectangle bounds;
				switch (element) {
				case TITLE:
					bounds = new Rectangle(clientArea.x, clientArea.y,
							clientArea.width, titleSize.y);
					break;
				case SUBTITLE:
					bounds = new Rectangle(clientArea.x, clientArea.y
							+ titleSize.y, clientArea.width, subTitleSize.y);
					break;
				case PLOT:
					bounds = new Rectangle(clientArea.x, clientArea.y
							+ titleAreaHeight, plotWidth, clientArea.height
							- titleAreaHeight);
					break;
				case LEGENDE:
					bounds = new Rectangle(clientArea.x + plotWidth,
							clientArea.y + titleAreaHeight, legendeWidth,
							clientArea.height - titleAreaHeight);
					break;
				default:
					bounds = new Rectangle(0, 0, 0, 0);
				}
				child.setBounds(bounds);
			}
		}
	}
}
