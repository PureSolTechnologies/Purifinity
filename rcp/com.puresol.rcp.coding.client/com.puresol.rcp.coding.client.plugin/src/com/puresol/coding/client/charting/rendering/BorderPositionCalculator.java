package com.puresol.coding.client.charting.rendering;

import org.eclipse.swt.graphics.Rectangle;

import com.puresol.coding.client.charting.BorderPosition;

/**
 * This class provides functionality to calculate the border area positions for
 * a client area.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BorderPositionCalculator {

    private final Rectangle clientArea;
    private final BorderPositionCounter areaCount;
    private final double relativeBorderAreaSize;

    private final Rectangle northArea;
    private final Rectangle eastArea;
    private final Rectangle southArea;
    private final Rectangle westArea;
    private final Rectangle centerArea;

    public BorderPositionCalculator(Rectangle clientArea,
	    BorderPositionCounter areaCount, double relativeBorderAreaSize) {
	super();
	this.clientArea = clientArea;
	this.areaCount = areaCount;
	this.relativeBorderAreaSize = relativeBorderAreaSize;
	northArea = getNorthArea();
	eastArea = getEastArea();
	southArea = getSouthArea();
	westArea = getWestArea();
	centerArea = new Rectangle(westArea.x + westArea.width - 1, northArea.y
		+ northArea.height - 1, clientArea.width - westArea.width
		- eastArea.width, clientArea.height - northArea.height
		- southArea.height);
    }

    private Rectangle getEastArea() {
	int width = 0;
	if (areaCount.hasPosition(BorderPosition.EAST)) {
	    width = getEastWestWith();
	}
	int height = getEastWestHeight();

	int x = clientArea.x + clientArea.width - width;
	int y = clientArea.y;
	if (areaCount.hasPosition(BorderPosition.NORTH)) {
	    y += (int) (clientArea.height * relativeBorderAreaSize);
	}
	return new Rectangle(x, y, width, height);
    }

    private Rectangle getNorthArea() {
	int width = getNorthSouthWidth();
	int height = 0;
	if (areaCount.hasPosition(BorderPosition.NORTH)) {
	    height = getNorthSouthHeight();
	}

	int x = clientArea.x;
	if (areaCount.hasPosition(BorderPosition.WEST)) {
	    x += (int) (clientArea.width * relativeBorderAreaSize);
	}
	int y = clientArea.y;
	return new Rectangle(x, y, width, height);
    }

    private Rectangle getSouthArea() {
	int width = getNorthSouthWidth();
	int height = 0;
	if (areaCount.hasPosition(BorderPosition.SOUTH)) {
	    height = getNorthSouthHeight();
	}

	int x = clientArea.x;
	if (areaCount.hasPosition(BorderPosition.WEST)) {
	    x += (int) (clientArea.width * relativeBorderAreaSize);
	}
	int y = clientArea.y + clientArea.height - height;
	return new Rectangle(x, y, width, height);
    }

    private Rectangle getWestArea() {
	int width = 0;
	if (areaCount.hasPosition(BorderPosition.WEST)) {
	    width = getEastWestWith();
	}
	int height = getEastWestHeight();

	int x = clientArea.x;
	int y = clientArea.y;
	if (areaCount.hasPosition(BorderPosition.NORTH)) {
	    y += (int) (clientArea.height * relativeBorderAreaSize);
	}
	return new Rectangle(x, y, width, height);
    }

    private int getNorthSouthHeight() {
	return (int) (clientArea.height * relativeBorderAreaSize);
    }

    private int getNorthSouthWidth() {
	int horizontalAreas = getNumberOfHorizontalAreas();
	return (int) (clientArea.width * (1.0 - horizontalAreas
		* relativeBorderAreaSize));
    }

    private int getEastWestHeight() {
	int verticalAreas = getNumberOfVerticalAreas();
	return (int) (clientArea.height * (1.0 - verticalAreas
		* relativeBorderAreaSize));
    }

    private int getEastWestWith() {
	return (int) (clientArea.width * relativeBorderAreaSize);
    }

    private int getNumberOfVerticalAreas() {
	int verticalAreas = 0;
	if (areaCount.hasPosition(BorderPosition.NORTH)) {
	    verticalAreas++;
	}
	if (areaCount.hasPosition(BorderPosition.SOUTH)) {
	    verticalAreas++;
	}
	return verticalAreas;
    }

    private int getNumberOfHorizontalAreas() {
	int horizontalAreas = 0;
	if (areaCount.hasPosition(BorderPosition.EAST)) {
	    horizontalAreas++;
	}
	if (areaCount.hasPosition(BorderPosition.WEST)) {
	    horizontalAreas++;
	}
	return horizontalAreas;
    }

    /**
     * Returns a rectangle with the position of the border area.
     * 
     * @param position
     *            is the border position.
     * @param num
     *            is the number of the layer.
     * @param total
     *            is the total number of layers.
     * @return
     */
    public Rectangle getPosition(BorderPosition position, int num, int total) {
	switch (position) {
	case EAST:
	    return getEastWestAreaPosition(num, total, eastArea);
	case NORTH:
	    return getNorthSouthAreaPosition(num, total, northArea);
	case SOUTH:
	    return getNorthSouthAreaPosition(num, total, southArea);
	case WEST:
	    return getEastWestAreaPosition(num, total, westArea);
	default:
	    throw new RuntimeException("Unknown border position constant "
		    + position + "!");
	}
    }

    private Rectangle getEastWestAreaPosition(int num, int count, Rectangle area) {
	int width = area.width;
	int height = (int) ((double) area.height / (double) count);
	int x = area.x;
	int y = area.y + height * num;
	return new Rectangle(x, y, width, height);
    }

    private Rectangle getNorthSouthAreaPosition(int num, int count,
	    Rectangle area) {
	int width = (int) ((double) area.width / (double) count);
	int height = area.height;
	int x = area.x + width * num;
	int y = area.y;
	return new Rectangle(x, y, width, height);
    }

    public Rectangle getCenterArea() {
	return new Rectangle(centerArea.x, centerArea.y, centerArea.width,
		centerArea.height);
    }

}
