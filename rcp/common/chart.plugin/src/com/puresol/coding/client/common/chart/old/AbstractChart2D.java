package com.puresol.coding.client.common.chart.old;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an abstract implementation of a 2D chart which is the foundation of
 * all 2D charts and 3D charts.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractChart2D implements Chart2D {

	private Legend legend = new Legend(BorderPosition.EAST);
	private final List<Title> titles = new ArrayList<Title>();

	public final void setLegend(Legend legend) {
		this.legend = legend;
	}

	@Override
	public final Legend getLegend() {
		return legend;
	}

	public final void addTitle(Title title) {
		titles.add(title);
	}

	@Override
	public final List<Title> getTitles() {
		return titles;
	}
}
