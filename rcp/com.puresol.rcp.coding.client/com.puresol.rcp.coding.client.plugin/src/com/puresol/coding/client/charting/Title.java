package com.puresol.coding.client.charting;

/**
 * This is an interface for a single chart title.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Title {

    /**
     * This method set the text for the title.
     * 
     * @param text
     */
    public void setText(String text);

    /**
     * This method returns the title's text.
     * 
     * @return
     */
    public String getText();

    /**
     * This method sets the size of the title to be drawn.
     * 
     * @param size
     */
    public void setSize(int size);

    /**
     * This method returns the size of the title font.
     * 
     * @return
     */
    public int getSize();
}
