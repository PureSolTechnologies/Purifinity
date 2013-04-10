package com.puresol.coding.client.common.chart.old;

import java.util.List;

/**
 * This is a general interface for a 2D chart. There are several different
 * charts which implement this interface.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface Chart2D {

	/**
	 * This method returns all titles of the graph.
	 * 
	 * @return A list of Title is returned containing the titles.
	 */
	public List<Title> getTitles();

	/**
	 * This method returns the legend of the chart.
	 * 
	 * @return
	 */
	public Legend getLegend();

}
