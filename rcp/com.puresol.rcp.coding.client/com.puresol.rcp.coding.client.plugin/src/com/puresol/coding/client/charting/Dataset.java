package com.puresol.coding.client.charting;

import org.swtchart.Chart;

/**
 * This interface represents a single dataset which is used to be put onto a
 * {@link Chart}.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Dataset {

    /**
     * This method adds a name to the dataset which can be drawn in a legend.
     * 
     * @param name
     */
    public void setName(String name);

}
