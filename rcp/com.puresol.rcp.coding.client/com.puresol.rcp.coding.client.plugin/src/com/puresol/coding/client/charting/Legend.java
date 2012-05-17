package com.puresol.coding.client.charting;

/**
 * This interface represents a legend of a chart.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Legend {

    private final LegendPosition position;

    public Legend(LegendPosition position) {
	super();
	this.position = position;
    }

    /**
     * This method returns the position of the legend in relationship to the
     * drawn graph.
     * 
     * @return
     */
    public LegendPosition getPosition() {
	return position;
    }

}
