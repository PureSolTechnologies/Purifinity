package com.puresol.coding.client.common.chart.old;


/**
 * This interface represents a legend of a chart.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Legend {

	private final BorderPosition position;

	public Legend(BorderPosition position) {
		super();
		this.position = position;
	}

	/**
	 * This method returns the position of the legend in relationship to the
	 * drawn graph.
	 * 
	 * @return
	 */
	public BorderPosition getPosition() {
		return position;
	}

}
