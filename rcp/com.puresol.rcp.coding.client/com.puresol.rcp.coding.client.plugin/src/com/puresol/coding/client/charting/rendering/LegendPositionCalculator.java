package com.puresol.coding.client.charting.rendering;

import java.util.Map;

import org.eclipse.swt.graphics.Rectangle;

import com.puresol.coding.client.charting.LegendPosition;

/**
 * This class provides functionality to calculate the legend positions for a
 * chart, where the whole chart area is known.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LegendPositionCalculator {

    private static final double RELATIVE_LEGEND_SIZE = 0.2;

    public static Rectangle getPosition(LegendPosition position, int num,
	    Map<LegendPosition, Integer> legendCount, Rectangle fullClientArea) {
	Rectangle legendArea;
	switch (position) {
	case EAST:
	    legendArea = getEastLegendArea(legendCount, fullClientArea);
	    return getLegendEastWestLegendPosition(num,
		    legendCount.get(LegendPosition.EAST), legendArea);
	case NORTH:
	    legendArea = getNorthLegendArea(legendCount, fullClientArea);
	    return getLegendNorthSouthLegendPosition(num,
		    legendCount.get(LegendPosition.NORTH), legendArea);
	case SOUTH:
	    legendArea = getSouthLegendArea(legendCount, fullClientArea);
	    return getLegendNorthSouthLegendPosition(num,
		    legendCount.get(LegendPosition.SOUTH), legendArea);
	case WEST:
	    legendArea = getWestLegendArea(legendCount, fullClientArea);
	    return getLegendEastWestLegendPosition(num,
		    legendCount.get(LegendPosition.WEST), legendArea);
	default:
	    throw new RuntimeException("Unknown legend position constant "
		    + position + "!");
	}
    }

    private static Rectangle getLegendEastWestLegendPosition(int num,
	    int count, Rectangle legendArea) {
	int width = legendArea.width;
	int height = (int) ((double) legendArea.height / (double) count);
	int x = legendArea.x;
	int y = legendArea.y + height * num;
	return new Rectangle(x, y, width, height);
    }

    private static Rectangle getLegendNorthSouthLegendPosition(int num,
	    int count, Rectangle legendArea) {
	int width = (int) ((double) legendArea.width / (double) count);
	int height = legendArea.height;
	int x = legendArea.x + width * num;
	int y = legendArea.y;
	return new Rectangle(x, y, width, height);
    }

    private static Rectangle getEastLegendArea(
	    Map<LegendPosition, Integer> legendCount, Rectangle fullClientArea) {
	int verticalLegends = getNumberOfVerticalLegends(legendCount);

	int width = getEastWestWith(fullClientArea);
	int height = getEastWestHeight(fullClientArea, verticalLegends);

	int x = fullClientArea.x + fullClientArea.width - width;
	int y = fullClientArea.y
		+ (int) (fullClientArea.height * RELATIVE_LEGEND_SIZE * legendCount
			.get(LegendPosition.NORTH));
	return new Rectangle(x, y, width, height);
    }

    private static Rectangle getNorthLegendArea(
	    Map<LegendPosition, Integer> legendCount, Rectangle fullClientArea) {
	int horizontalLegends = getNumberOfHorizontalLegends(legendCount);

	int width = getNorthSouthWidth(fullClientArea, horizontalLegends);
	int height = getNorthSouthHeight(fullClientArea);

	int x = fullClientArea.x
		+ (int) (fullClientArea.width * RELATIVE_LEGEND_SIZE * legendCount
			.get(LegendPosition.WEST));
	int y = fullClientArea.y;
	return new Rectangle(x, y, width, height);
    }

    private static Rectangle getSouthLegendArea(
	    Map<LegendPosition, Integer> legendCount, Rectangle fullClientArea) {
	int horizontalLegends = getNumberOfHorizontalLegends(legendCount);

	int width = getNorthSouthWidth(fullClientArea, horizontalLegends);
	int height = getNorthSouthHeight(fullClientArea);

	int x = fullClientArea.x
		+ (int) (fullClientArea.width * RELATIVE_LEGEND_SIZE * legendCount
			.get(LegendPosition.WEST));
	int y = fullClientArea.y + fullClientArea.height - height;
	return new Rectangle(x, y, width, height);
    }

    private static Rectangle getWestLegendArea(
	    Map<LegendPosition, Integer> legendCount, Rectangle fullClientArea) {
	int verticalLegends = getNumberOfVerticalLegends(legendCount);

	int width = getEastWestWith(fullClientArea);
	int height = getEastWestHeight(fullClientArea, verticalLegends);

	int x = fullClientArea.x;
	int y = fullClientArea.y
		+ (int) (fullClientArea.height * RELATIVE_LEGEND_SIZE * legendCount
			.get(LegendPosition.NORTH));
	return new Rectangle(x, y, width, height);
    }

    private static int getNorthSouthHeight(Rectangle fullClientArea) {
	int height = (int) (fullClientArea.height * RELATIVE_LEGEND_SIZE);
	return height;
    }

    private static int getNorthSouthWidth(Rectangle fullClientArea,
	    int horizontalLegends) {
	int width = (int) (fullClientArea.width * (1.0 - horizontalLegends
		* RELATIVE_LEGEND_SIZE));
	return width;
    }

    private static int getEastWestHeight(Rectangle fullClientArea,
	    int verticalLegends) {
	int height = (int) (fullClientArea.height * (1.0 - verticalLegends
		* RELATIVE_LEGEND_SIZE));
	return height;
    }

    private static int getEastWestWith(Rectangle fullClientArea) {
	int width = (int) (fullClientArea.width * RELATIVE_LEGEND_SIZE);
	return width;
    }

    private static int getNumberOfVerticalLegends(
	    Map<LegendPosition, Integer> legendCount) {
	int verticalLegends = (legendCount.get(LegendPosition.NORTH) > 0 ? 1
		: 0) + //
		(legendCount.get(LegendPosition.SOUTH) > 0 ? 1 : 0);
	return verticalLegends;
    }

    private static int getNumberOfHorizontalLegends(
	    Map<LegendPosition, Integer> legendCount) {
	int horizontalLegends = (legendCount.get(LegendPosition.EAST) > 0 ? 1
		: 0) + //
		(legendCount.get(LegendPosition.WEST) > 0 ? 1 : 0);
	return horizontalLegends;
    }

}
